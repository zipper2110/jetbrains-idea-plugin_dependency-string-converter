package com.litvin.dependency.model

/**
 * Enum representing the supported dependency declaration formats
 */
enum class DependencyFormat(val displayName: String) {
    GRADLE_GROOVY("Gradle Groovy"),  // Gradle with Groovy DSL (build.gradle)
    GRADLE_KOTLIN("Gradle Kotlin"),  // Gradle with Kotlin DSL (build.gradle.kts)
    MAVEN("Maven");          // Maven (pom.xml)

    companion object {
        // Basic Gradle Kotlin and Groovy patterns
        // Map notation pattern

        // Other common Gradle configurations
        val COMMON_GRADLE_CONFIGS = setOf(
            "implementation", "api", "compileOnly", "runtimeOnly", "annotationProcessor", "kapt",
            "testImplementation", "testApi", "testCompileOnly", "testRuntimeOnly", "testAnnotationProcessor",
            "debugImplementation", "releaseImplementation", "kaptTest", "developmentOnly",
            "runtimeClasspath", "testRuntimeClasspath", "androidTestImplementation", "androidTestApi"
        )

        /**
         * Detect the dependency format from the file name
         */
        fun fromFileName(fileName: String): DependencyFormat {
            return when {
                fileName.endsWith(".gradle.kts") -> GRADLE_KOTLIN
                fileName.endsWith(".gradle") -> GRADLE_GROOVY
                fileName.endsWith("pom.xml") -> MAVEN
                else -> throw IllegalArgumentException("Unsupported file type: $fileName")
            }
        }
    }
} 