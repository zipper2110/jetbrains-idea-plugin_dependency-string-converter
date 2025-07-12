package com.litvin.dependency.converter.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest10ArtifactOnly
import com.litvin.dependency.model.FileType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy artifact-only and file dependency formats
 */
class GradleGroovyDependencyParserTest10ArtifactOnly {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse single file dependency correctly`() {
        // Given
        val singleFile = GradleGroovyTest10ArtifactOnly.singleFile
        
        // When
        val result = parser.parse(singleFile)
        
        // Then
        assertNotNull(result)
        assertEquals("file", result.groupId)
        assertEquals("libs/custom.jar", result.artifactId)
        assertEquals("implementation", result.scope)
        
        // Verify file config
        assertNotNull(result.file)
        assertEquals(FileType.SINGLE_FILE, result.file?.type)
        assertEquals("libs/custom.jar", result.file?.path)
    }
    
    @Test
    fun `should parse file tree dependency correctly`() {
        // Given
        val fileTree = GradleGroovyTest10ArtifactOnly.fileTree
        
        // When
        val result = parser.parse(fileTree)
        
        // Then
        assertNotNull(result)
        assertEquals("file", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("implementation", result.scope)
        
        // Verify file config
        assertNotNull(result.file)
        assertEquals("libs", result.file?.path)
    }
    
    @Test
    fun `should parse directory dependency correctly`() {
        // Given
        val directory = GradleGroovyTest10ArtifactOnly.directory
        
        // When
        val result = parser.parse(directory)
        
        // Then
        assertNotNull(result)
        assertEquals("file", result.groupId)
        assertEquals("build/libs", result.artifactId)
        assertEquals("implementation", result.scope)
        
        // Verify file config
        assertNotNull(result.file)
        assertEquals("build/libs", result.file?.path)
    }
    
    @Test
    fun `should parse URL dependency correctly`() {
        // Given
        val url = GradleGroovyTest10ArtifactOnly.url
        
        // When
        val result = parser.parse(url)
        
        // Then
        assertNotNull(result)
        assertEquals("file", result.groupId)
        assertTrue(result.artifactId.contains("example.com"))
        assertEquals("implementation", result.scope)
        
        // Verify file config
        assertNotNull(result.file)
        assertTrue(result.file?.path?.contains("example.com") == true)
    }
    
    @Test
    fun `should parse dependency with classifier correctly`() {
        // Given
        val artifactWithClassifier = "implementation 'org.example:lib:1.0:sources'"
        
        // When
        val result = parser.parse(artifactWithClassifier)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("sources", result.classifier)
    }
} 