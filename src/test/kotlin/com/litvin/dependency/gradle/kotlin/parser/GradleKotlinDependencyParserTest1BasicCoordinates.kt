package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest1BasicCoordinates {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse basic dependency`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with classifier`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9:javadoc")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("javadoc", result.classifier)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with extension`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9@zip")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("zip", result.extension)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with classifier and extension`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9:javadoc@zip")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("javadoc", result.classifier)
        assertEquals("zip", result.extension)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with different scope`() {
        // Given
        val input = """
            testImplementation("org.springframework:spring-core:5.3.9")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("testImplementation", result.scope)
    }

    @Test
    fun `should parse dependency with different notation`() {
        // Given
        val input = """
            implementation(group = "org.springframework", name = "spring-core", version = "5.3.9")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with different notation and classifier`() {
        // Given
        val input = """
            implementation(group = "org.springframework", name = "spring-core", version = "5.3.9", classifier = "javadoc")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("javadoc", result.classifier)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with different notation and extension`() {
        // Given
        val input = """
            implementation(group = "org.springframework", name = "spring-core", version = "5.3.9", ext = "zip")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("zip", result.extension)
        assertEquals("implementation", result.scope)
    }
} 