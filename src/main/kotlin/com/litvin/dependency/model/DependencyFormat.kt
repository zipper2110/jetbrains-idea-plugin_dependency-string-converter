package com.litvin.dependency.model

/**
 * Enum representing the supported dependency declaration formats
 */
enum class DependencyFormat {
    GRADLE_GROOVY,  // Gradle with Groovy DSL (build.gradle)
    GRADLE_KOTLIN,  // Gradle with Kotlin DSL (build.gradle.kts)
    MAVEN;          // Maven (pom.xml)
    
    companion object {
        // Basic Gradle Kotlin and Groovy patterns
        private val GRADLE_KOTLIN_PATTERN = Regex("""implementation\s*\(["']""")
        private val GRADLE_GROOVY_PATTERN = Regex("""implementation\s+['"]""")
        private val TEST_IMPLEMENTATION_KOTLIN_PATTERN = Regex("""testImplementation\s*\(["']""")
        private val TEST_IMPLEMENTATION_GROOVY_PATTERN = Regex("""testImplementation\s+['"]""")
        
        // Map notation pattern
        private val GRADLE_MAP_NOTATION_PATTERN = Regex("""(\w+)\s+group:\s*['"]([^'"]+)['"],\s*name:\s*['"]([^'"]+)['"],\s*version:\s*['"]([^'"]+)['"]""")
        
        // Other common Gradle configurations
        private val COMMON_GRADLE_CONFIGS = listOf(
            "api", "compileOnly", "runtimeOnly", "annotationProcessor", "kapt", 
            "debugImplementation", "releaseImplementation", "testCompileOnly", "testRuntimeOnly",
            "testApi", "kaptTest", "developmentOnly", "runtimeClasspath", "testRuntimeClasspath"
        )
        
        // Version catalog patterns
        private val VERSION_CATALOG_PATTERN = Regex("""(\w+)\s+libs\.\w+""")
        private val VERSION_CATALOG_BUNDLE_PATTERN = Regex("""(\w+)\s+libs\.bundles\.\w+""")
        
        // Project dependency patterns
        private val PROJECT_DEPENDENCY_PATTERN = Regex("""(\w+)\s+project\(['"]:([^'"]+)['"]""")
        private val PROJECT_CONFIG_PATTERN = Regex("""(\w+)\s+project\(path:\s*['"]:([^'"]+)['"]""")
        
        // Platform and capability patterns
        private val PLATFORM_PATTERN = Regex("""(\w+)\s*\(\s*platform\(""")
        private val ENFORCED_PLATFORM_PATTERN = Regex("""(\w+)\s*\(\s*enforcedPlatform\(""")
        
        // Configuration block patterns
        private val EXCLUDE_PATTERN = Regex("""exclude\s+group:""")
        private val TRANSITIVE_PATTERN = Regex("""transitive\s*=\s*(true|false)""")
        private val ATTRIBUTE_PATTERN = Regex("""attribute\s*\(""")
        private val CAPABILITIES_PATTERN = Regex("""capabilities\s*\{""")
        
        // File dependency patterns
        private val FILE_PATTERN = Regex("""(\w+)\s+files?\(['"](.*?)['"]\)""")
        private val FILE_TREE_PATTERN = Regex("""(\w+)\s+fileTree\(['"](.*?)['"]\)""")
        private val ARTIFACT_PATTERN = Regex("""artifact\s*\{""")
        
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
            if (content.contains("<dependency>") || content.contains("<groupId>")) {
                return MAVEN
            }
            
            // Check for Gradle Kotlin DSL pattern
            if (GRADLE_KOTLIN_PATTERN.containsMatchIn(content) ||
                TEST_IMPLEMENTATION_KOTLIN_PATTERN.containsMatchIn(content) ||
                content.contains("implementation(") ||
                content.contains("testImplementation(") ||
                COMMON_GRADLE_CONFIGS.any { content.contains("$it(") }) {
                return GRADLE_KOTLIN
            }
            
            // Check for Gradle Groovy DSL pattern or map notation
            if (GRADLE_GROOVY_PATTERN.containsMatchIn(content) ||
                TEST_IMPLEMENTATION_GROOVY_PATTERN.containsMatchIn(content) ||
                GRADLE_MAP_NOTATION_PATTERN.containsMatchIn(content) ||
                VERSION_CATALOG_PATTERN.containsMatchIn(content) ||
                VERSION_CATALOG_BUNDLE_PATTERN.containsMatchIn(content) ||
                PROJECT_DEPENDENCY_PATTERN.containsMatchIn(content) ||
                PROJECT_CONFIG_PATTERN.containsMatchIn(content) ||
                PLATFORM_PATTERN.containsMatchIn(content) ||
                ENFORCED_PLATFORM_PATTERN.containsMatchIn(content) ||
                EXCLUDE_PATTERN.containsMatchIn(content) ||
                TRANSITIVE_PATTERN.containsMatchIn(content) ||
                ATTRIBUTE_PATTERN.containsMatchIn(content) ||
                CAPABILITIES_PATTERN.containsMatchIn(content) ||
                FILE_PATTERN.containsMatchIn(content) ||
                FILE_TREE_PATTERN.containsMatchIn(content) ||
                ARTIFACT_PATTERN.containsMatchIn(content) ||
                content.contains("platform(") ||
                content.contains("enforcedPlatform(") ||
                content.contains("files(") ||
                content.contains("fileTree(") ||
                COMMON_GRADLE_CONFIGS.any { content.contains("$it '") }) {
                return GRADLE_GROOVY
            }
            
            return null
        }
    }
} 