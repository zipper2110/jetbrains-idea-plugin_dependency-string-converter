package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest2VersionSpecification
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy version specification formats
 */
class GradleGroovyDependencyParserTest2VersionSpecification {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse dynamic latest version correctly`() {
        // Given
        val latestVersion = GradleGroovyTest2VersionSpecification.latestVersion
        
        // When
        val result = parser.parse(latestVersion)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("latest.release", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse dynamic plus notation correctly`() {
        // Given
        val plusNotation = GradleGroovyTest2VersionSpecification.plusNotation
        
        // When
        val result = parser.parse(plusNotation)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.+", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse dynamic latest minor correctly`() {
        // Given
        val latestMinor = GradleGroovyTest2VersionSpecification.latestMinor
        
        // When
        val result = parser.parse(latestMinor)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.+", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse snapshot version correctly`() {
        // Given
        val snapshotVersion = GradleGroovyTest2VersionSpecification.snapshotVersion
        
        // When
        val result = parser.parse(snapshotVersion)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("1.0.0-SNAPSHOT", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse release candidate version correctly`() {
        // Given
        val rcVersion = GradleGroovyTest2VersionSpecification.rcVersion
        
        // When
        val result = parser.parse(rcVersion)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("1.0.0-rc.1", result.version)
        assertEquals("implementation", result.scope)
    }
} 