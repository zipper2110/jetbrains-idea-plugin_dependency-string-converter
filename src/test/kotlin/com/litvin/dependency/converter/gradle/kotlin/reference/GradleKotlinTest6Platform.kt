package com.litvin.dependency.converter.gradle.kotlin.reference

/**
 * Examples of Gradle Kotlin DSL platform dependency formats
 */
object GradleKotlinTest6Platform {
    // Basic platform
    val basicPlatform = """
        implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3"))
    """.trimIndent()
    
    // Platform with dependency
    val platformWithDependency = """
        implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3"))
        implementation("org.springframework.boot:spring-boot-starter")
    """.trimIndent()
    
    // Multiple platforms
    val multiplePlatforms = """
        implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3"))
        implementation(platform("com.google.cloud:libraries-bom:24.0.0"))
    """.trimIndent()
    
    // Platform with enforced version
    val platformEnforcedVersion = """
        implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:2.6.3"))
    """.trimIndent()
    
    // Platform with constraints
    val platformWithConstraints = """
        implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
            version {
                strictly("[2.6.0,3.0.0)")
            }
        }
    """.trimIndent()
    
    // Platform with capabilities
    val platformWithCapabilities = """
        implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
            capabilities {
                requireCapability("org.springframework.boot:platform-capability")
            }
        }
    """.trimIndent()
    
    // Platform with attributes
    val platformWithAttributes = """
        implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
            }
        }
    """.trimIndent()
    
    // Custom platform
    val customPlatform = """
        dependencies {
            constraints {
                api("org.springframework:spring-core:5.3.9")
                api("org.springframework:spring-context:5.3.9")
                api("org.springframework:spring-beans:5.3.9")
            }
        }
    """.trimIndent()
    
    // Platform with exclusions
    val platformWithExclusions = """
        implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }
    """.trimIndent()
    
    // Complex platform combination
    val complexPlatformCombination = """
        implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
            version {
                strictly("[2.6.0,3.0.0)")
            }
            capabilities {
                requireCapability("org.springframework.boot:platform-capability")
            }
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
            }
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }
    """.trimIndent()
} 