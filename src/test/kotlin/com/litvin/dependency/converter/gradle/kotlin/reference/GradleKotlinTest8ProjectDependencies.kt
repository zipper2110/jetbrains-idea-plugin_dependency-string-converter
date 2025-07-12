package com.litvin.dependency.converter.gradle.kotlin.reference

/**
 * Examples of Gradle Kotlin DSL project dependency formats
 */
object GradleKotlinTest8ProjectDependencies {
    // Simple project dependency
    val simpleProject = """
        implementation(project(":core"))
    """.trimIndent()
    
    // Nested project dependency
    val nestedProject = """
        implementation(project(":feature:auth"))
    """.trimIndent()
    
    // Project dependency with configuration
    val projectWithConfiguration = """
        implementation(project(":core")) {
            targetConfiguration = "api"
        }
    """.trimIndent()
    
    // Project dependency with build type
    val projectWithBuildType = """
        implementation(project(":core")) {
            targetConfiguration = "debug"
        }
    """.trimIndent()
    
    // Project dependency with transitive false
    val projectWithTransitiveFalse = """
        implementation(project(":core")) {
            isTransitive = false
        }
    """.trimIndent()
    
    // Project dependency with exclude
    val projectWithExclude = """
        implementation(project(":core")) {
            exclude(group = "org.example", module = "excluded")
        }
    """.trimIndent()
    
    // Project dependency with multiple excludes
    val projectWithMultipleExcludes = """
        implementation(project(":core")) {
            exclude(group = "org.example", module = "excluded1")
            exclude(group = "org.example", module = "excluded2")
        }
    """.trimIndent()
    
    // Project dependency with capability
    val projectWithCapability = """
        implementation(project(":core")) {
            capabilities {
                requireCapability("org.example:feature:1.0.0")
            }
        }
    """.trimIndent()
    
    // Project dependency with attributes
    val projectWithAttributes = """
        implementation(project(":core")) {
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, Usage.JAVA_RUNTIME)
            }
        }
    """.trimIndent()
    
    // Project dependency with multiple configurations
    val projectWithMultipleConfigurations = """
        implementation(project(":core")) {
            targetConfiguration = "api"
            isTransitive = false
            exclude(group = "org.example", module = "excluded")
            capabilities {
                requireCapability("org.example:feature:1.0.0")
            }
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, Usage.JAVA_RUNTIME)
            }
        }
    """.trimIndent()
} 