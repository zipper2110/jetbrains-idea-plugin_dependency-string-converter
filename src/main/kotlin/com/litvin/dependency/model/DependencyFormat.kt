package com.litvin.dependency.model

/**
 * Enum representing the supported dependency declaration formats
 */
enum class DependencyFormat {
    GRADLE_GROOVY,  // Gradle with Groovy DSL (build.gradle)
    GRADLE_KOTLIN,  // Gradle with Kotlin DSL (build.gradle.kts)
    MAVEN;          // Maven (pom.xml)
    
    companion object {
        private val GRADLE_KOTLIN_PATTERN = Regex("""implementation\s*\(["']""")
        private val GRADLE_GROOVY_PATTERN = Regex("""implementation\s+['"]""")
        private val TEST_IMPLEMENTATION_KOTLIN_PATTERN = Regex("""testImplementation\s*\(["']""")
        private val TEST_IMPLEMENTATION_GROOVY_PATTERN = Regex("""testImplementation\s+['"]""")
        
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
        
        /**
         * Detect the dependency format from the dependency content
         */
        fun fromContent(content: String): DependencyFormat? {
            // Check for Maven format first - needs both <dependency> tags
            if (content.contains("<dependency>") && content.contains("</dependency>")) {
                return MAVEN
            }
            
            // Check for Gradle Kotlin DSL pattern
            if (GRADLE_KOTLIN_PATTERN.containsMatchIn(content) ||
                TEST_IMPLEMENTATION_KOTLIN_PATTERN.containsMatchIn(content)) {
                return GRADLE_KOTLIN
            }
            
            // Check for Gradle Groovy DSL pattern
            if (GRADLE_GROOVY_PATTERN.containsMatchIn(content) ||
                TEST_IMPLEMENTATION_GROOVY_PATTERN.containsMatchIn(content)) {
                return GRADLE_GROOVY
            }
            
            return null
        }
    }
} 