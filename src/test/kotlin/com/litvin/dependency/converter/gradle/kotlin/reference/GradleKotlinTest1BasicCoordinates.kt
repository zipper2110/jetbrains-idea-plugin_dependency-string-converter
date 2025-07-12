package com.litvin.dependency.converter.gradle.kotlin.reference

/**
 * Examples of basic Gradle Kotlin DSL dependency coordinate formats
 */
object GradleKotlinTest1BasicCoordinates {
    // Basic string notation
    val basicStringNotation = """
        implementation("org.springframework:spring-core:5.3.9")
    """.trimIndent()
    
    // Group-module-version notation
    val groupModuleVersion = """
        implementation(group = "org.springframework", name = "spring-core", version = "5.3.9")
    """.trimIndent()
    
    // API dependency
    val apiDependency = """
        api("com.google.guava:guava:31.1-jre")
    """.trimIndent()
    
    // CompileOnly dependency
    val compileOnlyDependency = """
        compileOnly("org.projectlombok:lombok:1.18.22")
    """.trimIndent()
    
    // Implementation without version
    val noVersionDependency = """
        implementation("org.springframework:spring-core")
    """.trimIndent()
    
    // Common company module
    val companyModuleDependency = """
        implementation("com.mycompany.project:module-name:1.0.0")
    """.trimIndent()
    
    // Spring Boot starter
    val springBootStarterDependency = """
        implementation("org.springframework.boot:spring-boot-starter:2.6.3")
    """.trimIndent()
    
    // Quarkus dependency
    val quarkusDependency = """
        implementation("io.quarkus:quarkus-core:2.7.5.Final")
    """.trimIndent()
} 