import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.4.0"
}

group = "com.litvin.dependency.converter"
version = "0.0.3"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    
    intellijPlatform {
        intellijIdeaCommunity("2025.1")
        bundledPlugin("com.intellij.java")
    }
}

intellijPlatform {
    pluginConfiguration {
        id.set("com.litvin.dependency.converter")
        name.set("Maven/Gradle Dependency Converter/Formatter")
        description.set("Automatically converts between Maven and Gradle dependency formats when copying and pasting.")
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            apiVersion.set(KotlinVersion.KOTLIN_2_1)
            languageVersion.set(KotlinVersion.KOTLIN_2_1)
            freeCompilerArgs.set(listOf("-Xjvm-default=all"))
        }
    }

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed", "standardOut", "standardError")
            showStandardStreams = true
            showExceptions = true
            showCauses = true
            showStackTraces = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            afterSuite(KotlinClosure2<TestDescriptor, TestResult, Unit>({ descriptor, result ->
                if (descriptor.parent == null) {
                    println("\nTest result: ${result.resultType}")
                    println("Test summary: ${result.testCount} tests, " +
                            "${result.successfulTestCount} succeeded, " +
                            "${result.failedTestCount} failed, " +
                            "${result.skippedTestCount} skipped")
                }
            }))
        }
    }

    register("extractDependencyStrings") {
        group = "verification"
        description = "Extracts dependency strings from reference test files"
        
        doLast {
            val testResourcesDir = file("src/test/resources")
            testResourcesDir.mkdirs()
            
            val referenceDirs = mapOf(
                "gradle-kotlin" to "src/test/kotlin/com/litvin/dependency/gradle/kotlin/reference",
                "gradle-groovy" to "src/test/kotlin/com/litvin/dependency/gradle/groovy/reference",
                "maven" to "src/test/kotlin/com/litvin/dependency/maven/reference"
            )
            
            referenceDirs.forEach { (type, dirPath) ->
                val outputFile = file("src/test/resources/dependency-strings-$type.txt")
                val dependencyStrings = mutableListOf<String>()
                
                val referenceDir = file(dirPath)
                val filePrefix = when (type) {
                    "gradle-kotlin" -> "GradleKotlinTest"
                    "gradle-groovy" -> "GradleGroovyTest"
                    "maven" -> "MavenParserTest"
                    else -> ""
                }
                
                val referenceFiles = referenceDir.listFiles { file -> 
                    file.isFile && file.name.endsWith(".kt") && file.name.startsWith(filePrefix)
                } ?: emptyArray()
                
                referenceFiles.forEach { file ->
                    dependencyStrings.add("// From ${file.name}")
                    val content = file.readText()
                    val pattern = "val\\s+\\w+\\s*=\\s*\"\"\"(.*?)\"\"\"".toRegex(setOf(RegexOption.DOT_MATCHES_ALL))
                    val matches = pattern.findAll(content)
                    matches.forEach { match ->
                        val dependency = match.groupValues[1].trimIndent()
                        if (dependency.isNotEmpty()) {
                            dependencyStrings.add(dependency)
                        }
                    }
                }
                
                outputFile.writeText(dependencyStrings.joinToString("\n"))
            }
        }
    }

    named("compileTestKotlin") {
        dependsOn("extractDependencyStrings")
    }
}