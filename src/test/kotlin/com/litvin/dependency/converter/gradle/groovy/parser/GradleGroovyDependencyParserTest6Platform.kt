package com.litvin.dependency.converter.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest6Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy platform dependency formats
 */
class GradleGroovyDependencyParserTest6Platform {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse basic platform dependency correctly`() {
        // Given
        val basicPlatform = GradleGroovyTest6Platform.basicPlatform
        
        // When
        val result = parser.parse(basicPlatform)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.7.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("platform", result.type)
    }
    
    @Test
    fun `should parse enforced platform dependency correctly`() {
        // Given
        val enforcedPlatform = GradleGroovyTest6Platform.enforcedPlatform
        
        // When
        val result = parser.parse(enforcedPlatform)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.7.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("enforced-platform", result.type)
    }
    
    @Test
    fun `should parse platform with exclusions correctly`() {
        // Given
        val platformWithExclusions = GradleGroovyTest6Platform.platformWithExclusions
        
        // When
        val result = parser.parse(platformWithExclusions)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.7.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("platform", result.type)
        
        // Verify exclusions
        assertFalse(result.config.exclusions.isEmpty())
        assertEquals(1, result.config.exclusions.size)
        assertEquals("org.apache.logging.log4j", result.config.exclusions[0].groupId)
        assertEquals("log4j-to-slf4j", result.config.exclusions[0].artifactId)
    }
    
    @Test
    fun `should parse platform with capabilities correctly`() {
        // Given
        val platformWithCapabilities = GradleGroovyTest6Platform.platformWithCapabilities
        
        // When
        val result = parser.parse(platformWithCapabilities)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.7.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("platform", result.type)
        
        // Verify capabilities
        assertFalse(result.config.capabilities.isEmpty())
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.springframework.boot", result.config.capabilities[0].group)
        assertEquals("spring-boot-platform", result.config.capabilities[0].name)
    }
    
    @Test
    fun `should parse custom platform dependency correctly`() {
        // Given
        val customPlatform = GradleGroovyTest6Platform.customPlatform
        
        // When
        val result = parser.parse(customPlatform)
        
        // Then
        assertNotNull(result)
        // For project platform dependency
        assertEquals("platform", result.type)
        assertTrue(result.project != null || result.artifactId.contains("platform"))
    }
} 