package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest2VersionSpecification {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse version range inclusive`() {
        // Given
        val input = "implementation 'org.example:my-library:[1.0.0,2.0.0]'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[1.0.0,2.0.0]", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse version range exclusive`() {
        // Given
        val input = "implementation 'org.example:my-library:(1.0.0,2.0.0)'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("(1.0.0,2.0.0)", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse version range mixed`() {
        // Given
        val input = "implementation 'org.example:my-library:[1.0.0,2.0.0)'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[1.0.0,2.0.0)", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse latest version`() {
        // Given
        val input = "implementation 'org.example:my-library:latest.release'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("latest.release", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dynamic version with plus`() {
        // Given
        val input = "implementation 'org.example:my-library:1.+'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.+", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dynamic version with asterisk`() {
        // Given
        val input = "implementation 'org.example:my-library:1.*'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.*", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse snapshot version`() {
        // Given
        val input = "implementation 'org.example:my-library:1.0.0-SNAPSHOT'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0-SNAPSHOT", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse release candidate version`() {
        // Given
        val input = "implementation 'org.example:my-library:1.0.0-RC1'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0-RC1", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse milestone version`() {
        // Given
        val input = "implementation 'org.example:my-library:1.0.0-M1'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0-M1", result.version)
        assertEquals("implementation", result.scope)
    }
} 