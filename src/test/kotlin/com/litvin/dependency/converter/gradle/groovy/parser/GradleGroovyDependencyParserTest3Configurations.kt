package com.litvin.dependency.converter.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest3Configurations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy configuration formats
 */
class GradleGroovyDependencyParserTest3Configurations {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse implementation configuration correctly`() {
        // Given
        val implementation = GradleGroovyTest3Configurations.implementation
        
        // When
        val result = parser.parse(implementation)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse api configuration correctly`() {
        // Given
        val api = GradleGroovyTest3Configurations.api
        
        // When
        val result = parser.parse(api)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("api", result.scope)
    }
    
    @Test
    fun `should parse compileOnly configuration correctly`() {
        // Given
        val compileOnly = GradleGroovyTest3Configurations.compileOnly
        
        // When
        val result = parser.parse(compileOnly)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("compileOnly", result.scope)
    }
    
    @Test
    fun `should parse runtimeOnly configuration correctly`() {
        // Given
        val runtimeOnly = GradleGroovyTest3Configurations.runtimeOnly
        
        // When
        val result = parser.parse(runtimeOnly)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("runtimeOnly", result.scope)
    }
    
    @Test
    fun `should parse testImplementation configuration correctly`() {
        // Given
        val testImplementation = GradleGroovyTest3Configurations.testImplementation
        
        // When
        val result = parser.parse(testImplementation)
        
        // Then
        assertNotNull(result)
        assertEquals("org.junit.jupiter", result.groupId)
        assertEquals("junit-jupiter", result.artifactId)
        assertEquals("5.8.2", result.version)
        assertEquals("testImplementation", result.scope)
    }
    
    @Test
    fun `should parse custom configuration correctly`() {
        // Given
        val customConfiguration = GradleGroovyTest3Configurations.customConfiguration
        
        // When
        val result = parser.parse(customConfiguration)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("custom-lib", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("myCustomConfiguration", result.scope)
    }
} 