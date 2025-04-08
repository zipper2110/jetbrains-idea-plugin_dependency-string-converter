package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.CapabilityModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest7Variants {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse debug variant dependency`() {
        // Given
        val input = """
            debugImplementation('com.example:library:1.0.0')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("debugImplementation", result.scope)
    }

    @Test
    fun `should parse release variant dependency`() {
        // Given
        val input = """
            releaseImplementation('com.example:library:1.0.0')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("releaseImplementation", result.scope)
    }

    @Test
    fun `should parse flavor variant dependency`() {
        // Given
        val input = """
            paidImplementation('com.example:library:1.0.0')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("paidImplementation", result.scope)
    }

    @Test
    fun `should parse combined flavor and build type dependency`() {
        // Given
        val input = """
            paidDebugImplementation('com.example:library:1.0.0')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("paidDebugImplementation", result.scope)
    }

    @Test
    fun `should parse variant specific project dependency`() {
        // Given
        val input = """
            debugImplementation(project(':core'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("debugImplementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":core", result.projectPath)
    }

    @Test
    fun `should parse variant specific file dependency`() {
        // Given
        val input = """
            debugImplementation(files('libs/custom.jar'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("custom", result.artifactId)
        assertEquals("debugImplementation", result.scope)
        assertTrue(result.isFile)
        assertEquals("libs/custom.jar", result.filePath)
    }

    @Test
    fun `should parse variant specific dependency with configuration`() {
        // Given
        val input = """
            debugImplementation('com.example:library:1.0.0') {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("debugImplementation", result.scope)
        assertFalse(result.transitive)
    }

    @Test
    fun `should parse variant specific dependency with capability`() {
        // Given
        val input = """
            debugImplementation('com.example:library:1.0.0') {
                capabilities {
                    requireCapability('com.example:feature:1.0.0')
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("debugImplementation", result.scope)
        assertEquals(1, result.capabilities.size)
        assertEquals("com.example", result.capabilities[0].group)
        assertEquals("feature", result.capabilities[0].name)
        assertEquals("1.0.0", result.capabilities[0].version)
    }
} 