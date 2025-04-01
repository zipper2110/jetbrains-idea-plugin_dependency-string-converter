package com.litvin.dependency.maven.parser

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.maven.reference.MavenParserTest5Classifier
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of various classifier formats
 */
class MavenDependencyParserTest5Classifier {
    private val parser = MavenDependencyParser()
    
    @Test
    fun `should parse dependency without classifier`() {
        // Given
        val noClassifierXml = MavenParserTest5Classifier.noClassifierXml
        
        // When
        val result = parser.parse(noClassifierXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(null, result.classifier)
    }
    
    @Test
    fun `should parse dependency with sources classifier`() {
        // Given
        val sourcesClassifierXml = MavenParserTest5Classifier.sourcesClassifierXml
        
        // When
        val result = parser.parse(sourcesClassifierXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("sources", result.classifier)
    }
    
    @Test
    fun `should parse dependency with javadoc classifier`() {
        // Given
        val javadocClassifierXml = MavenParserTest5Classifier.javadocClassifierXml
        
        // When
        val result = parser.parse(javadocClassifierXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("javadoc", result.classifier)
    }
    
    @Test
    fun `should parse dependency with tests classifier`() {
        // Given
        val testsClassifierXml = MavenParserTest5Classifier.testsClassifierXml
        
        // When
        val result = parser.parse(testsClassifierXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("tests", result.classifier)
    }
    
    @Test
    fun `should parse dependency with natives-windows classifier`() {
        // Given
        val nativeWindowsClassifierXml = MavenParserTest5Classifier.nativeWindowsClassifierXml
        
        // When
        val result = parser.parse(nativeWindowsClassifierXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.lwjgl", result.groupId)
        assertEquals("lwjgl", result.artifactId)
        assertEquals("3.3.1", result.version)
        assertEquals("natives-windows", result.classifier)
    }
    
    @Test
    fun `should parse dependency with jdk11 classifier`() {
        // Given
        val jdk11ClassifierXml = MavenParserTest5Classifier.jdk11ClassifierXml
        
        // When
        val result = parser.parse(jdk11ClassifierXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("custom-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("jdk11", result.classifier)
    }
    
    @Test
    fun `should parse dependency with javadoc classifier and jar type`() {
        // Given
        val javadocWithTypeXml = MavenParserTest5Classifier.javadocWithTypeXml
        
        // When
        val result = parser.parse(javadocWithTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("jar", result.type)
        assertEquals("javadoc", result.classifier)
    }
    
    @Test
    fun `should parse dependency with sources classifier and provided scope`() {
        // Given
        val sourcesWithScopeXml = MavenParserTest5Classifier.sourcesWithScopeXml
        
        // When
        val result = parser.parse(sourcesWithScopeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("sources", result.classifier)
        assertEquals("compileOnly", result.scope)
    }
} 