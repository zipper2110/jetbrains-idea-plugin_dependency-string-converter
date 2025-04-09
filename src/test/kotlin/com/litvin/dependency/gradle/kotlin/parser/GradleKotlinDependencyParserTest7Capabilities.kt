package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest7Capabilities {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse dependency with single capability`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                capabilities {
                    requireCapability("org.springframework:spring-core-capability:1.0")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.springframework", result.config.capabilities[0].group)
        assertEquals("spring-core-capability", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
    }

    @Test
    fun `should parse dependency with multiple capabilities`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                capabilities {
                    requireCapability("org.springframework:spring-core-capability:1.0")
                    requireCapability("org.springframework:spring-core-feature:1.0")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(2, result.config.capabilities.size)
        assertEquals("org.springframework", result.config.capabilities[0].group)
        assertEquals("spring-core-capability", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
        assertEquals("org.springframework", result.config.capabilities[1].group)
        assertEquals("spring-core-feature", result.config.capabilities[1].name)
        assertEquals("1.0", result.config.capabilities[1].version)
    }

    @Test
    fun `should parse dependency with capabilities and other configurations`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                capabilities {
                    requireCapability("org.springframework:spring-core-capability:1.0")
                    requireCapability("org.springframework:spring-core-feature:1.0")
                }
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                }
                exclude(group = "commons-logging", module = "commons-logging")
                targetConfiguration = "default"
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("default", result.config.targetConfiguration)
        assertEquals(2, result.config.capabilities.size)
        assertEquals("org.springframework", result.config.capabilities[0].group)
        assertEquals("spring-core-capability", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
        assertEquals("org.springframework", result.config.capabilities[1].group)
        assertEquals("spring-core-feature", result.config.capabilities[1].name)
        assertEquals("1.0", result.config.capabilities[1].version)
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
    }

    @Test
    fun `should parse dependency with capability without version`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                capabilities {
                    requireCapability("org.springframework:spring-core-capability")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.springframework", result.config.capabilities[0].group)
        assertEquals("spring-core-capability", result.config.capabilities[0].name)
        assertEquals("", result.config.capabilities[0].version)
    }
} 