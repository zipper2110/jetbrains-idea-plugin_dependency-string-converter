package com.litvin.dependency.converter.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest7Capabilities
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy capability formats
 */
class GradleGroovyDependencyParserTest7Capabilities {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse basic capability correctly`() {
        // Given
        val basicCapability = GradleGroovyTest7Capabilities.basicCapability
        
        // When
        val result = parser.parse(basicCapability)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify capabilities
        assertFalse(result.config.capabilities.isEmpty())
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.example", result.config.capabilities[0].group)
        assertEquals("special-feature", result.config.capabilities[0].name)
    }
    
    @Test
    fun `should parse multiple capabilities correctly`() {
        // Given
        val multipleCapabilities = GradleGroovyTest7Capabilities.multipleCapabilities
        
        // When
        val result = parser.parse(multipleCapabilities)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify capabilities
        assertFalse(result.config.capabilities.isEmpty())
        assertEquals(2, result.config.capabilities.size)
        
        // Find the capabilities by name
        val feature1 = result.config.capabilities.find { it.name == "feature1" }
        val feature2 = result.config.capabilities.find { it.name == "feature2" }
        
        // Verify each capability
        assertNotNull(feature1)
        assertEquals("org.example", feature1!!.group)
        assertEquals("feature1", feature1.name)
        assertEquals("1.0", feature1.version)
        
        assertNotNull(feature2)
        assertEquals("org.example", feature2!!.group)
        assertEquals("feature2", feature2.name)
        assertEquals("1.0", feature2.version)
    }
    
    @Test
    fun `should parse capability with version correctly`() {
        // Given
        val capabilityWithVersion = GradleGroovyTest7Capabilities.capabilityWithVersion
        
        // When
        val result = parser.parse(capabilityWithVersion)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify capabilities
        assertFalse(result.config.capabilities.isEmpty())
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.example", result.config.capabilities[0].group)
        assertEquals("feature", result.config.capabilities[0].name)
        assertEquals("2.0", result.config.capabilities[0].version)
    }
    
    @Test
    fun `should parse complex capability combination correctly`() {
        // Given
        val complexCapabilityCombination = GradleGroovyTest7Capabilities.complexCapabilityCombination
        
        // When
        val result = parser.parse(complexCapabilityCombination)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify capabilities
        assertFalse(result.config.capabilities.isEmpty())
        assertTrue(result.config.capabilities.size >= 1)
        
        // Check for at least feature1 and feature2
        assertTrue(result.config.capabilities.any { it.name == "feature1" })
        assertTrue(result.config.capabilities.any { it.name == "feature2" })
    }
} 