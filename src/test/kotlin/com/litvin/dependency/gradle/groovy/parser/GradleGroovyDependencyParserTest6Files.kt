package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest6Files {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse single file dependency`() {
        // Given
        val input = """
            implementation(files('libs/custom.jar'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("custom", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isFile)
        assertEquals("libs/custom.jar", result.filePath)
    }

    @Test
    fun `should parse file tree dependency`() {
        // Given
        val input = """
            implementation(fileTree('libs') {
                include '*.jar'
            })
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isFileTree)
        assertEquals("libs", result.filePath)
        assertEquals(listOf("*.jar"), result.fileTreeIncludes)
    }

    @Test
    fun `should parse file tree dependency with multiple patterns`() {
        // Given
        val input = """
            implementation(fileTree('libs') {
                include '*.jar'
                include '*.aar'
                exclude 'test-*.jar'
            })
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isFileTree)
        assertEquals("libs", result.filePath)
        assertEquals(listOf("*.jar", "*.aar"), result.fileTreeIncludes)
        assertEquals(listOf("test-*.jar"), result.fileTreeExcludes)
    }

    @Test
    fun `should parse directory dependency`() {
        // Given
        val input = """
            implementation(files('build/classes/java/main'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("build", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isDirectory)
        assertEquals("build/classes/java/main", result.filePath)
    }

    @Test
    fun `should parse url dependency`() {
        // Given
        val input = """
            implementation(files(new URL('https://example.com/lib.jar')))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("remote", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isUrl)
        assertEquals("https://example.com/lib.jar", result.filePath)
    }

    @Test
    fun `should parse file dependency with configuration`() {
        // Given
        val input = """
            implementation(files('libs/custom.jar')) {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("custom", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isFile)
        assertEquals("libs/custom.jar", result.filePath)
        assertFalse(result.transitive)
    }

    @Test
    fun `should parse file dependency with builtBy`() {
        // Given
        val input = """
            implementation(files('libs/custom.jar').builtBy('customTask'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("custom", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isFile)
        assertEquals("libs/custom.jar", result.filePath)
        assertEquals(listOf("customTask"), result.builtBy)
    }
} 