package com.litvin.dependency.converter.maven.parser

import com.litvin.dependency.converter.maven.reference.MavenParserTest4Scope
import com.litvin.dependency.converter.parser.MavenDependencyParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various scope formats from MavenDimension4Scope
 */
class MavenDependencyParserTest4Scope {
    private val parser = MavenDependencyParser()
    
    @Test
    fun `should parse default scope correctly`() {
        // Given
        val defaultScopeXml = MavenParserTest4Scope.defaultScopeXml
        
        // When
        val result = parser.parse(defaultScopeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope) // Default scope should be implementation
    }
    
    @Test
    fun `should parse compile scope correctly`() {
        // Given
        val compileScopeXml = MavenParserTest4Scope.compileScopeXml
        
        // When
        val result = parser.parse(compileScopeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope) // 'compile' maps to 'implementation'
    }
    
    @Test
    fun `should parse runtime scope correctly`() {
        // Given
        val runtimeScopeXml = MavenParserTest4Scope.runtimeScopeXml
        
        // When
        val result = parser.parse(runtimeScopeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.postgresql", result.groupId)
        assertEquals("postgresql", result.artifactId)
        assertEquals("42.3.1", result.version)
        assertEquals("runtimeOnly", result.scope) // 'runtime' maps to 'runtimeOnly'
    }
    
    @Test
    fun `should parse test scope correctly`() {
        // Given
        val testScopeXml = MavenParserTest4Scope.testScopeXml
        
        // When
        val result = parser.parse(testScopeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("junit", result.groupId)
        assertEquals("junit", result.artifactId)
        assertEquals("4.13.2", result.version)
        assertEquals("testImplementation", result.scope) // 'test' maps to 'testImplementation'
    }
    
    @Test
    fun `should parse provided scope correctly`() {
        // Given
        val providedScopeXml = MavenParserTest4Scope.providedScopeXml
        
        // When
        val result = parser.parse(providedScopeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("javax.servlet", result.groupId)
        assertEquals("javax.servlet-api", result.artifactId)
        assertEquals("4.0.1", result.version)
        assertEquals("compileOnly", result.scope) // 'provided' maps to 'compileOnly'
    }
    
    @Test
    fun `should parse system scope correctly`() {
        // Given
        val systemScopeXml = MavenParserTest4Scope.systemScopeXml
        
        // When
        val result = parser.parse(systemScopeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("com.oracle", result.groupId)
        assertEquals("ojdbc8", result.artifactId)
        assertEquals("19.3.0", result.version)
        assertEquals("implementation", result.scope) // 'system' maps to 'implementation'
        assertEquals("\${basedir}/lib/ojdbc8.jar", result.systemPath) // Check systemPath
    }
} 