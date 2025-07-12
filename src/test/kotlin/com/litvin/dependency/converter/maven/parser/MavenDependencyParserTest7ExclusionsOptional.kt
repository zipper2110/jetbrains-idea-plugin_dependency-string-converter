package com.litvin.dependency.converter.maven.parser

import com.litvin.dependency.converter.maven.reference.MavenParserTest7ExclusionsOptional
import com.litvin.dependency.converter.parser.MavenDependencyParser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of Maven dependencies with exclusions and optional flags
 */
class MavenDependencyParserTest7ExclusionsOptional {
    private val parser = MavenDependencyParser()
    
    @Test
    fun `should parse single exclusion correctly`() {
        // Given
        val singleExclusionXml = MavenParserTest7ExclusionsOptional.singleExclusionXml
        
        // When
        val result = parser.parse(singleExclusionXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
        assertFalse(result.optional)
    }
    
    @Test
    fun `should parse multiple exclusions correctly`() {
        // Given
        val multipleExclusionsXml = MavenParserTest7ExclusionsOptional.multipleExclusionsXml
        
        // When
        val result = parser.parse(multipleExclusionsXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(2, result.config.exclusions.size)
        
        // First exclusion
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
        
        // Second exclusion
        assertEquals("log4j", result.config.exclusions[1].groupId)
        assertEquals("log4j", result.config.exclusions[1].artifactId)
        
        assertFalse(result.optional)
    }
    
    @Test
    fun `should parse wildcard exclusion correctly`() {
        // Given
        val wildcardExclusionXml = MavenParserTest7ExclusionsOptional.wildcardExclusionXml
        
        // When
        val result = parser.parse(wildcardExclusionXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("*", result.config.exclusions[0].artifactId)
        assertFalse(result.optional)
    }
    
    @Test
    fun `should parse optional dependency correctly`() {
        // Given
        val optionalDependencyXml = MavenParserTest7ExclusionsOptional.optionalDependencyXml
        
        // When
        val result = parser.parse(optionalDependencyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("com.h2database", result.groupId)
        assertEquals("h2", result.artifactId)
        assertEquals("2.1.210", result.version)
        assertTrue(result.config.exclusions.isEmpty())
        assertTrue(result.optional)
    }
    
    @Test
    fun `should parse exclusions and optional flag correctly`() {
        // Given
        val exclusionsAndOptionalXml = MavenParserTest7ExclusionsOptional.exclusionsAndOptionalXml
        
        // When
        val result = parser.parse(exclusionsAndOptionalXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-context", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
        assertTrue(result.optional)
    }
    
    @Test
    fun `should parse exclusion with scope and type correctly`() {
        // Given
        val exclusionWithScopeAndTypeXml = MavenParserTest7ExclusionsOptional.exclusionWithScopeAndTypeXml
        
        // When
        val result = parser.parse(exclusionWithScopeAndTypeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-context", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("testImplementation", result.scope) // 'test' maps to 'testImplementation'
        assertEquals("jar", result.type)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
        assertFalse(result.optional)
    }
    
    @Test
    fun `should parse optional with classifier correctly`() {
        // Given
        val optionalWithClassifierXml = MavenParserTest7ExclusionsOptional.optionalWithClassifierXml
        
        // When
        val result = parser.parse(optionalWithClassifierXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.postgresql", result.groupId)
        assertEquals("postgresql", result.artifactId)
        assertEquals("42.3.1", result.version)
        assertEquals("jre7", result.classifier)
        assertTrue(result.optional)
        assertTrue(result.config.exclusions.isEmpty())
    }
} 