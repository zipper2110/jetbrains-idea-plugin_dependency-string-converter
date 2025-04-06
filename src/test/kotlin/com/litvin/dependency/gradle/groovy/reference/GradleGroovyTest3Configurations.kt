package com.litvin.dependency.gradle.groovy.reference

/**
 * Examples of Gradle Groovy configuration formats
 */
object GradleGroovyTest3Configurations {
    // Implementation configuration
    val implementation = """
        implementation 'org.springframework:spring-core:5.3.9'
    """.trimIndent()
    
    // API configuration
    val api = """
        api 'org.springframework:spring-core:5.3.9'
    """.trimIndent()
    
    // CompileOnly configuration
    val compileOnly = """
        compileOnly 'org.springframework:spring-core:5.3.9'
    """.trimIndent()
    
    // RuntimeOnly configuration
    val runtimeOnly = """
        runtimeOnly 'org.springframework:spring-core:5.3.9'
    """.trimIndent()
    
    // TestImplementation configuration
    val testImplementation = """
        testImplementation 'org.junit.jupiter:junit-jupiter:5.8.2'
    """.trimIndent()
    
    // TestCompileOnly configuration
    val testCompileOnly = """
        testCompileOnly 'org.projectlombok:lombok:1.18.22'
    """.trimIndent()
    
    // TestRuntimeOnly configuration
    val testRuntimeOnly = """
        testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.2'
    """.trimIndent()
    
    // AnnotationProcessor configuration
    val annotationProcessor = """
        annotationProcessor 'org.projectlombok:lombok:1.18.22'
    """.trimIndent()
    
    // Kapt configuration (Kotlin annotation processing)
    val kapt = """
        kapt 'org.mapstruct:mapstruct-processor:1.4.2.Final'
    """.trimIndent()
    
    // Debug configuration
    val debugImplementation = """
        debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.8.1'
    """.trimIndent()
    
    // Release configuration
    val releaseImplementation = """
        releaseImplementation 'com.example:release-only-lib:1.0.0'
    """.trimIndent()
    
    // Custom configuration
    val customConfiguration = """
        myCustomConfiguration 'org.example:custom-lib:1.0.0'
    """.trimIndent()
    
    // Multiple configurations
    val multipleConfigurations = """
        implementation 'org.springframework:spring-core:5.3.9'
        runtimeOnly 'org.springframework:spring-aspects:5.3.9'
        testImplementation 'org.junit.jupiter:junit-jupiter:5.8.2'
        testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.2'
    """.trimIndent()
} 