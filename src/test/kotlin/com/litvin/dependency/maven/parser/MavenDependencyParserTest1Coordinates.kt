package com.litvin.dependency.maven.parser

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.maven.reference.MavenParserTest1Coordinates
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of various Maven coordinate formats from MavenDimension1Coordinates
 */
class MavenDependencyParserTest1Coordinates {
    private val parser = MavenDependencyParser()
    
    @Test
    fun `should parse Spring Core XML correctly`() {
        // Given
        val springCoreXml = MavenParserTest1Coordinates.springCoreXml
        
        // When
        val result = parser.parse(springCoreXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse Guava XML correctly`() {
        // Given
        val guavaXml = MavenParserTest1Coordinates.guavaXml
        
        // When
        val result = parser.parse(guavaXml)
        
        // Then
        assertNotNull(result)
        assertEquals("com.google.guava", result.groupId)
        assertEquals("guava", result.artifactId)
        assertEquals("31.1-jre", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse Commons Lang XML correctly`() {
        // Given
        val commonsLangXml = MavenParserTest1Coordinates.commonsLangXml
        
        // When
        val result = parser.parse(commonsLangXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.apache.commons", result.groupId)
        assertEquals("commons-lang3", result.artifactId)
        assertEquals("3.12.0", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse company module XML correctly`() {
        // Given
        val companyModuleXml = MavenParserTest1Coordinates.companyModuleXml
        
        // When
        val result = parser.parse(companyModuleXml)
        
        // Then
        assertNotNull(result)
        assertEquals("com.mycompany.project", result.groupId)
        assertEquals("module-name", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse Spring Boot starter XML correctly`() {
        // Given
        val springBootStarterXml = MavenParserTest1Coordinates.springBootStarterXml
        
        // When
        val result = parser.parse(springBootStarterXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-starter", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse Quarkus Core XML correctly`() {
        // Given
        val quarkusCoreXml = MavenParserTest1Coordinates.quarkusCoreXml
        
        // When
        val result = parser.parse(quarkusCoreXml)
        
        // Then
        assertNotNull(result)
        assertEquals("io.quarkus", result.groupId)
        assertEquals("quarkus-core", result.artifactId)
        assertEquals("2.7.5.Final", result.version)
        assertEquals("implementation", result.scope)
    }
} 