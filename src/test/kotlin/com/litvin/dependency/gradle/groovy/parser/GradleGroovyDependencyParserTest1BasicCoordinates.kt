package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest1BasicCoordinates {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse basic implementation dependency`() {
        // Given
        val input = "implementation 'org.springframework:spring-core:5.3.9'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency without version`() {
        // Given
        val input = "implementation 'org.springframework:spring-core'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertNull(result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with double quotes`() {
        // Given
        val input = """implementation "org.springframework:spring-core:5.3.9""""

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse api dependency`() {
        // Given
        val input = "api 'com.google.guava:guava:31.1-jre'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.google.guava", result.groupId)
        assertEquals("guava", result.artifactId)
        assertEquals("31.1-jre", result.version)
        assertEquals("api", result.scope)
    }

    @Test
    fun `should parse compileOnly dependency`() {
        // Given
        val input = "compileOnly 'org.projectlombok:lombok:1.18.22'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.projectlombok", result.groupId)
        assertEquals("lombok", result.artifactId)
        assertEquals("1.18.22", result.version)
        assertEquals("compileOnly", result.scope)
    }

    @Test
    fun `should parse testImplementation dependency`() {
        // Given
        val input = "testImplementation 'org.junit.jupiter:junit-jupiter:5.8.2'"

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.junit.jupiter", result.groupId)
        assertEquals("junit-jupiter", result.artifactId)
        assertEquals("5.8.2", result.version)
        assertEquals("testImplementation", result.scope)
    }

    @Test
    fun `should throw exception for invalid format`() {
        // Given
        val input = "implementation org.springframework:spring-core:5.3.9"

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            parser.parse(input)
        }
    }
} 