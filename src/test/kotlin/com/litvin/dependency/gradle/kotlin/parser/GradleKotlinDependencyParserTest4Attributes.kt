package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest4Attributes {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse dependency with single attribute`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
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
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
    }

    @Test
    fun `should parse dependency with multiple attributes`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                    attribute(Category.CATEGORY_ATTRIBUTE, "library")
                    attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, "jar")
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
        assertEquals(3, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
        assertEquals("Category.CATEGORY_ATTRIBUTE", result.config.attributes[1].key)
        assertEquals("library", result.config.attributes[1].value)
        assertEquals("LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE", result.config.attributes[2].key)
        assertEquals("jar", result.config.attributes[2].value)
    }

    @Test
    fun `should parse dependency with custom attribute`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                attributes {
                    attribute("custom.attribute", "custom-value")
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
        assertEquals(1, result.config.attributes.size)
        assertEquals("custom.attribute", result.config.attributes[0].key)
        assertEquals("custom-value", result.config.attributes[0].value)
    }

    @Test
    fun `should parse dependency with attributes and other configurations`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                isTransitive = false
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                    attribute(Category.CATEGORY_ATTRIBUTE, "library")
                }
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
        assertFalse(result.config.transitive)
        assertEquals("default", result.config.targetConfiguration)
        assertEquals(2, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
        assertEquals("Category.CATEGORY_ATTRIBUTE", result.config.attributes[1].key)
        assertEquals("library", result.config.attributes[1].value)
    }
} 