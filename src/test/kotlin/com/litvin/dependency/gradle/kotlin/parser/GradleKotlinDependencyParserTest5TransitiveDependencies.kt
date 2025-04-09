package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest5TransitiveDependencies {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse dependency with single exclusion`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                exclude(group = "commons-logging", module = "commons-logging")
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
    }

    @Test
    fun `should parse dependency with multiple exclusions`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                exclude(group = "commons-logging", module = "commons-logging")
                exclude(group = "org.slf4j", module = "slf4j-api")
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(2, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
        assertEquals("org.slf4j", result.config.exclusions[1].groupId)
        assertEquals("slf4j-api", result.config.exclusions[1].artifactId)
    }

    @Test
    fun `should parse dependency with wildcard exclusion`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                exclude(group = "commons-logging", module = "*")
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("*", result.config.exclusions[0].artifactId)
    }

    @Test
    fun `should parse dependency with exclusions and other configurations`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                isTransitive = false
                exclude(group = "commons-logging", module = "commons-logging")
                exclude(group = "org.slf4j", module = "slf4j-api")
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
        assertEquals(2, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
        assertEquals("org.slf4j", result.config.exclusions[1].groupId)
        assertEquals("slf4j-api", result.config.exclusions[1].artifactId)
    }

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
    fun `should parse dependency with transitive true`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                isTransitive = true
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        assertTrue(result.config.transitive)
    }
} 