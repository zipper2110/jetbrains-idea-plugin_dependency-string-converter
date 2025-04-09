package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest3Configurations {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse dependency with transitive false`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                isTransitive = false
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
    }

    @Test
    fun `should parse dependency with changing true`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                isChanging = true
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertTrue(result.config.changing)
    }

    @Test
    fun `should parse dependency with force true`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                isForce = true
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertTrue(result.config.force)
    }

    @Test
    fun `should parse dependency with target configuration`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
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
    }

    @Test
    fun `should parse dependency with multiple configurations`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                isTransitive = false
                isChanging = true
                isForce = true
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
        assertTrue(result.config.changing)
        assertTrue(result.config.force)
        assertEquals("default", result.config.targetConfiguration)
    }
} 