package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest7Files {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse file dependency`() {
        // Given
        val input = """
            implementation files('libs/mylib.jar')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("libs/mylib.jar", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.SINGLE_FILE, result.file?.type)
        assertEquals("libs/mylib.jar", result.file?.path)
    }

    @Test
    fun `should parse file tree dependency`() {
        // Given
        val input = """
            implementation fileTree('libs') {
                include '*.jar'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("fileTree", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.FILE_TREE, result.file?.type)
        assertEquals("libs", result.file?.path)
        assertEquals(listOf("*.jar"), result.file?.includes)
    }

    @Test
    fun `should parse file tree dependency with multiple includes`() {
        // Given
        val input = """
            implementation fileTree('libs') {
                include '*.jar'
                include '*.aar'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("fileTree", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.FILE_TREE, result.file?.type)
        assertEquals("libs", result.file?.path)
        assertEquals(listOf("*.jar", "*.aar"), result.file?.includes)
    }

    @Test
    fun `should parse file tree dependency with excludes`() {
        // Given
        val input = """
            implementation fileTree('libs') {
                include '*.jar'
                exclude '*-sources.jar'
                exclude '*-javadoc.jar'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("fileTree", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.FILE_TREE, result.file?.type)
        assertEquals("libs", result.file?.path)
        assertEquals(listOf("*.jar"), result.file?.includes)
        assertEquals(listOf("*-sources.jar", "*-javadoc.jar"), result.file?.excludes)
    }

    @Test
    fun `should parse file tree dependency with builtBy`() {
        // Given
        val input = """
            implementation fileTree('libs') {
                include '*.jar'
                builtBy 'generateJars'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("fileTree", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.FILE_TREE, result.file?.type)
        assertEquals("libs", result.file?.path)
        assertEquals(listOf("*.jar"), result.file?.includes)
        assertEquals(listOf("generateJars"), result.file?.builtBy)
    }

    @Test
    fun `should parse file tree dependency with multiple builtBy`() {
        // Given
        val input = """
            implementation fileTree('libs') {
                include '*.jar'
                builtBy 'generateJars'
                builtBy 'processResources'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("fileTree", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.FILE_TREE, result.file?.type)
        assertEquals("libs", result.file?.path)
        assertEquals(listOf("*.jar"), result.file?.includes)
        assertEquals(listOf("generateJars", "processResources"), result.file?.builtBy)
    }

    @Test
    fun `should parse directory dependency`() {
        // Given
        val input = """
            implementation dir('libs')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("dir", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.DIRECTORY, result.file?.type)
        assertEquals("libs", result.file?.path)
    }

    @Test
    fun `should parse url dependency`() {
        // Given
        val input = """
            implementation url('https://example.com/mylib.jar')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("url", result.groupId)
        assertEquals("https://example.com/mylib.jar", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.URL, result.file?.type)
        assertEquals("https://example.com/mylib.jar", result.file?.path)
    }
} 