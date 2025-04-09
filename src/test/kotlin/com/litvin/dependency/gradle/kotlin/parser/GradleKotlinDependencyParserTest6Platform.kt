package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest6Platform {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse basic platform dependency`() {
        // Given
        val input = """
            implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3"))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("pom", result.type)
    }

    @Test
    fun `should parse enforced platform dependency`() {
        // Given
        val input = """
            implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:2.6.3"))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("pom", result.type)
        assertTrue(result.config.force)
    }

    @Test
    fun `should parse platform dependency with capabilities`() {
        // Given
        val input = """
            implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
                capabilities {
                    requireCapability("org.springframework.boot:platform-capability:1.0")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("pom", result.type)
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.springframework.boot", result.config.capabilities[0].group)
        assertEquals("platform-capability", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
    }

    @Test
    fun `should parse platform dependency with attributes`() {
        // Given
        val input = """
            implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("pom", result.type)
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
    }

    @Test
    fun `should parse platform dependency with exclusions`() {
        // Given
        val input = """
            implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
                exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("pom", result.type)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("org.springframework.boot", result.config.exclusions[0].groupId)
        assertEquals("spring-boot-starter-logging", result.config.exclusions[0].artifactId)
    }

    @Test
    fun `should parse platform dependency with multiple configurations`() {
        // Given
        val input = """
            implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3")) {
                capabilities {
                    requireCapability("org.springframework.boot:platform-capability:1.0")
                }
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                }
                exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
                targetConfiguration = "default"
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("pom", result.type)
        assertEquals("default", result.config.targetConfiguration)
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.springframework.boot", result.config.capabilities[0].group)
        assertEquals("platform-capability", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("org.springframework.boot", result.config.exclusions[0].groupId)
        assertEquals("spring-boot-starter-logging", result.config.exclusions[0].artifactId)
    }
} 