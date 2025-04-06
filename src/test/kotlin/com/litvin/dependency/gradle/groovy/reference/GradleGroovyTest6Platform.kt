package com.litvin.dependency.gradle.groovy.reference

/**
 * Examples of Gradle Groovy platform dependency formats
 */
object GradleGroovyTest6Platform {
    // Basic platform
    val basicPlatform = """
        implementation platform('org.springframework.boot:spring-boot-dependencies:2.7.0')
    """.trimIndent()

    // Enforced platform
    val enforcedPlatform = """
        implementation enforcedPlatform('org.springframework.boot:spring-boot-dependencies:2.7.0')
    """.trimIndent()

    // Platform with version constraints
    val platformWithConstraints = """
        implementation platform('org.springframework.boot:spring-boot-dependencies:2.7.0') {
            version {
                strictly '[2.7.0,3.0.0)'
            }
        }
    """.trimIndent()

    // Multiple platforms
    val multiplePlatforms = """
        implementation platform('org.springframework.boot:spring-boot-dependencies:2.7.0')
        implementation platform('org.testcontainers:testcontainers-bom:1.16.3')
    """.trimIndent()

    // Platform with exclusions
    val platformWithExclusions = """
        implementation platform('org.springframework.boot:spring-boot-dependencies:2.7.0') {
            exclude group: 'org.apache.logging.log4j', module: 'log4j-to-slf4j'
        }
    """.trimIndent()

    // Platform with capabilities
    val platformWithCapabilities = """
        implementation platform('org.springframework.boot:spring-boot-dependencies:2.7.0') {
            capabilities {
                requireCapability('org.springframework.boot:spring-boot-platform')
            }
        }
    """.trimIndent()

    // Custom platform
    val customPlatform = """
        implementation platform(project(':platform'))
    """.trimIndent()

    // Platform with dependency constraints
    val platformWithDependencyConstraints = """
        dependencies {
            constraints {
                implementation(platform('org.springframework.boot:spring-boot-dependencies:2.7.0')) {
                    because 'align platform versions'
                }
            }
        }
    """.trimIndent()

    // Platform with rich version constraints
    val platformWithRichVersions = """
        implementation platform('org.springframework.boot:spring-boot-dependencies:2.7.0') {
            version {
                require '[2.7.0,3.0.0)'
                prefer '2.7.0'
                reject '2.7.1'
            }
        }
    """.trimIndent()

    // Platform with attributes
    val platformWithAttributes = """
        implementation platform('org.springframework.boot:spring-boot-dependencies:2.7.0') {
            attributes {
                attribute(Category.CATEGORY_ATTRIBUTE, 'platform')
            }
        }
    """.trimIndent()
}