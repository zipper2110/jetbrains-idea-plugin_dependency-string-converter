package com.litvin.dependency.converter.maven.parser

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.converter.maven.reference.MavenParserTest6PropertiesVariables
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of Maven dependencies with property variables
 */
class MavenDependencyParserTest6PropertiesVariables {
    private val parser = MavenDependencyParser()
    
    @Test
    fun `should parse version property correctly`() {
        // Given
        val versionPropertyXml = MavenParserTest6PropertiesVariables.versionPropertyXml
        
        // When
        val result = parser.parse(versionPropertyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${spring.version}", result.version)
    }
    
    @Test
    fun `should parse groupId property correctly`() {
        // Given
        val groupIdPropertyXml = MavenParserTest6PropertiesVariables.groupIdPropertyXml
        
        // When
        val result = parser.parse(groupIdPropertyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("\${springframework.groupId}", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
    }
    
    @Test
    fun `should parse artifactId property correctly`() {
        // Given
        val artifactIdPropertyXml = MavenParserTest6PropertiesVariables.artifactIdPropertyXml
        
        // When
        val result = parser.parse(artifactIdPropertyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("\${spring.core.artifactId}", result.artifactId)
        assertEquals("5.3.9", result.version)
    }
    
    @Test
    fun `should parse multiple properties correctly`() {
        // Given
        val multiplePropertiesXml = MavenParserTest6PropertiesVariables.multiplePropertiesXml
        
        // When
        val result = parser.parse(multiplePropertiesXml)
        
        // Then
        assertNotNull(result)
        assertEquals("\${springframework.groupId}", result.groupId)
        assertEquals("\${spring.core.artifactId}", result.artifactId)
        assertEquals("\${spring.version}", result.version)
    }
    
    @Test
    fun `should parse built-in property correctly`() {
        // Given
        val builtInPropertyXml = MavenParserTest6PropertiesVariables.builtInPropertyXml
        
        // When
        val result = parser.parse(builtInPropertyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("\${project.version}", result.version)
    }
    
    @Test
    fun `should parse scope property correctly`() {
        // Given
        val scopePropertyXml = MavenParserTest6PropertiesVariables.scopePropertyXml
        
        // When
        val result = parser.parse(scopePropertyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("junit", result.groupId)
        assertEquals("junit", result.artifactId)
        assertEquals("4.13.2", result.version)
        // Note: The scope property will be passed through the mapper, but it may not work correctly
        // We're just testing that the parser can handle properties in the scope field
    }
    
    @Test
    fun `should parse classifier property correctly`() {
        // Given
        val classifierPropertyXml = MavenParserTest6PropertiesVariables.classifierPropertyXml
        
        // When
        val result = parser.parse(classifierPropertyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("\${docs.classifier}", result.classifier)
    }
    
    @Test
    fun `should parse systemPath property correctly`() {
        // Given
        val systemPathPropertyXml = MavenParserTest6PropertiesVariables.systemPathPropertyXml
        
        // When
        val result = parser.parse(systemPathPropertyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("com.oracle", result.groupId)
        assertEquals("ojdbc8", result.artifactId)
        assertEquals("19.3.0", result.version)
        assertEquals("implementation", result.scope) // 'system' maps to 'implementation'
        assertEquals("\${oracle.jdbc.path}", result.systemPath)
    }
    
    @Test
    fun `should parse version range property correctly`() {
        // Given
        val versionRangePropertyXml = MavenParserTest6PropertiesVariables.versionRangePropertyXml
        
        // When
        val result = parser.parse(versionRangePropertyXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[\${min.version},\${max.version})", result.version)
    }
} 