package com.litvin.dependency.converter.maven.parser

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.converter.maven.reference.MavenParserTest3TypePackaging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of various type/packaging formats
 */
class MavenDependencyParserTest3TypePackaging {
    private val parser = MavenDependencyParser()
    
    @Test
    fun `should parse dependency with default type`() {
        // Given
        val defaultTypeXml = MavenParserTest3TypePackaging.defaultTypeXml
        
        // When
        val result = parser.parse(defaultTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("jar", result.type) // Default type is jar
    }
    
    @Test
    fun `should parse dependency with jar type`() {
        // Given
        val jarTypeXml = MavenParserTest3TypePackaging.jarTypeXml
        
        // When
        val result = parser.parse(jarTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("jar", result.type)
    }
    
    @Test
    fun `should parse dependency with war type`() {
        // Given
        val warTypeXml = MavenParserTest3TypePackaging.warTypeXml
        
        // When
        val result = parser.parse(warTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("sample-webapp", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("war", result.type)
    }
    
    @Test
    fun `should parse dependency with ear type`() {
        // Given
        val earTypeXml = MavenParserTest3TypePackaging.earTypeXml
        
        // When
        val result = parser.parse(earTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("enterprise-app", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("ear", result.type)
    }
    
    @Test
    fun `should parse dependency with pom type`() {
        // Given
        val pomTypeXml = MavenParserTest3TypePackaging.pomTypeXml
        
        // When
        val result = parser.parse(pomTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("pom", result.type)
    }
    
    @Test
    fun `should parse dependency with maven-plugin type`() {
        // Given
        val mavenPluginTypeXml = MavenParserTest3TypePackaging.mavenPluginTypeXml
        
        // When
        val result = parser.parse(mavenPluginTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.apache.maven.plugins", result.groupId)
        assertEquals("maven-compiler-plugin", result.artifactId)
        assertEquals("3.10.1", result.version)
        assertEquals("maven-plugin", result.type)
    }
    
    @Test
    fun `should parse dependency with test-jar type`() {
        // Given
        val testJarTypeXml = MavenParserTest3TypePackaging.testJarTypeXml
        
        // When
        val result = parser.parse(testJarTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("library-with-test-utils", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("test-jar", result.type)
    }
    
    @Test
    fun `should parse dependency with ejb type`() {
        // Given
        val ejbTypeXml = MavenParserTest3TypePackaging.ejbTypeXml
        
        // When
        val result = parser.parse(ejbTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("enterprise-bean", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("ejb", result.type)
    }
    
    @Test
    fun `should parse dependency with bundle type`() {
        // Given
        val bundleTypeXml = MavenParserTest3TypePackaging.bundleTypeXml
        
        // When
        val result = parser.parse(bundleTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("osgi-bundle", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("bundle", result.type)
    }
    
    @Test
    fun `should parse dependency with zip type`() {
        // Given
        val zipTypeXml = MavenParserTest3TypePackaging.zipTypeXml
        
        // When
        val result = parser.parse(zipTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("resources-package", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("zip", result.type)
    }
} 