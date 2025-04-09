package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest1BasicCoordinates
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy coordinate formats
 */
class GradleGroovyDependencyParserTest1BasicCoordinates {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse basic string notation correctly`() {
        // Given
        val basicStringNotation = GradleGroovyTest1BasicCoordinates.basicStringNotation
        
        // When
        val result = parser.parse(basicStringNotation)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse map notation correctly`() {
        // Given
        val mapNotation = GradleGroovyTest1BasicCoordinates.mapNotation
        
        // When
        val result = parser.parse(mapNotation)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse API dependency correctly`() {
        // Given
        val apiDependency = GradleGroovyTest1BasicCoordinates.apiDependency
        
        // When
        val result = parser.parse(apiDependency)
        
        // Then
        assertNotNull(result)
        assertEquals("com.google.guava", result.groupId)
        assertEquals("guava", result.artifactId)
        assertEquals("31.1-jre", result.version)
        assertEquals("api", result.scope)
    }
    
    @Test
    fun `should parse compileOnly dependency correctly`() {
        // Given
        val compileOnlyDependency = GradleGroovyTest1BasicCoordinates.compileOnlyDependency
        
        // When
        val result = parser.parse(compileOnlyDependency)
        
        // Then
        assertNotNull(result)
        assertEquals("org.projectlombok", result.groupId)
        assertEquals("lombok", result.artifactId)
        assertEquals("1.18.22", result.version)
        assertEquals("compileOnly", result.scope)
    }
    
    @Test
    fun `should parse dependency without version correctly`() {
        // Given
        val noVersionDependency = GradleGroovyTest1BasicCoordinates.noVersionDependency
        
        // When
        val result = parser.parse(noVersionDependency)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals(null, result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse company module dependency correctly`() {
        // Given
        val companyModuleDependency = GradleGroovyTest1BasicCoordinates.companyModuleDependency
        
        // When
        val result = parser.parse(companyModuleDependency)
        
        // Then
        assertNotNull(result)
        assertEquals("com.mycompany.project", result.groupId)
        assertEquals("module-name", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
    }
} 