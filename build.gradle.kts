plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
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
        intellijIdeaCommunity("2024.1")
        bundledPlugin("com.intellij.java")
    }
}

intellijPlatform {
    pluginConfiguration {
        id.set("com.litvin.dependency.converter")
        name.set("Maven/Gradle Dependency Converter/Formatter")
        vendor.set("Dmitri Litvin")
        description.set("Automatically converts between Maven and Gradle dependency formats when copying and pasting.")
        
        buildFeatures {
            buildSearchableOptions.set(false)
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            apiVersion = "1.9"
            languageVersion = "1.9"
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }

    test {
        useJUnitPlatform()
    }
} 