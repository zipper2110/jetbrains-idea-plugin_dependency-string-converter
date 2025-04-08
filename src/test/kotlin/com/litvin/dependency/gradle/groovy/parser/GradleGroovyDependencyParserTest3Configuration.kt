package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest3Configuration {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse dependency with transitive false`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertFalse(result.transitive)
    }

    @Test
    fun `should parse dependency with changing true`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                changing = true
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertTrue(result.changing)
    }

    @Test
    fun `should parse dependency with force true`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                force = true
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertTrue(result.force)
    }

    @Test
    fun `should parse dependency with exclude`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                exclude group: 'org.excluded', module: 'excluded-module'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.exclusions.size)
        assertEquals("org.excluded", result.exclusions[0].groupId)
        assertEquals("excluded-module", result.exclusions[0].artifactId)
    }

    @Test
    fun `should parse dependency with multiple excludes`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                exclude group: 'org.excluded1', module: 'excluded-module1'
                exclude group: 'org.excluded2', module: 'excluded-module2'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(2, result.exclusions.size)
        assertEquals("org.excluded1", result.exclusions[0].groupId)
        assertEquals("excluded-module1", result.exclusions[0].artifactId)
        assertEquals("org.excluded2", result.exclusions[1].groupId)
        assertEquals("excluded-module2", result.exclusions[1].artifactId)
    }

    @Test
    fun `should parse dependency with classifier`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0:sources') {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("sources", result.classifier)
        assertFalse(result.transitive)
    }

    @Test
    fun `should parse dependency with extension`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0@jar') {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("jar", result.type)
        assertFalse(result.transitive)
    }

    @Test
    fun `should parse dependency with multiple configuration options`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                transitive = false
                changing = true
                force = true
                exclude group: 'org.excluded', module: 'excluded-module'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertFalse(result.transitive)
        assertTrue(result.changing)
        assertTrue(result.force)
        assertEquals(1, result.exclusions.size)
        assertEquals("org.excluded", result.exclusions[0].groupId)
        assertEquals("excluded-module", result.exclusions[0].artifactId)
    }
} 