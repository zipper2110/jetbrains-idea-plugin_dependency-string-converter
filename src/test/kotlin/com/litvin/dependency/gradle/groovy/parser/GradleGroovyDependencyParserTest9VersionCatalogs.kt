package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest9VersionCatalogs
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy version catalog formats
 */
class GradleGroovyDependencyParserTest9VersionCatalogs {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse basic catalog reference correctly`() {
        // Given
        val basicCatalogReference = GradleGroovyTest9VersionCatalogs.basicCatalogReference
        
        // When
        val result = parser.parse(basicCatalogReference)
        
        // Then
        assertNotNull(result)
        assertEquals("libs.spring.core", result.artifactId)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse catalog with exclusion correctly`() {
        // Given
        val catalogWithConfiguration = GradleGroovyTest9VersionCatalogs.catalogWithConfiguration
        
        // When
        val result = parser.parse(catalogWithConfiguration)
        
        // Then
        assertNotNull(result)
        assertTrue(result.artifactId.contains("spring.core"))
        assertEquals("implementation", result.scope)
        
        // Verify exclusions
        assertFalse(result.config.exclusions.isEmpty())
        assertTrue(result.config.exclusions.any { it.groupId == "commons-logging" })
    }
    
    @Test
    fun `should parse catalog bundle correctly`() {
        // Given
        val catalogBundle = GradleGroovyTest9VersionCatalogs.catalogBundle
        
        // When
        val result = parser.parse(catalogBundle)
        
        // Then
        assertNotNull(result)
        assertTrue(result.artifactId.contains("bundles.spring"))
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun `should parse catalog with capabilities correctly`() {
        // Given
        val catalogWithCapabilities = GradleGroovyTest9VersionCatalogs.catalogWithCapabilities
        
        // When
        val result = parser.parse(catalogWithCapabilities)
        
        // Then
        assertNotNull(result)
        assertTrue(result.artifactId.contains("spring.core"))
        assertEquals("implementation", result.scope)
        
        // Verify capabilities
        assertFalse(result.config.capabilities.isEmpty())
        assertTrue(result.config.capabilities.any { 
            it.group == "org.springframework" && it.name == "spring-core-capability"
        })
    }
    
    @Test
    fun `should parse catalog with attributes correctly`() {
        // Given
        val catalogWithAttributes = GradleGroovyTest9VersionCatalogs.catalogWithAttributes
        
        // When
        val result = parser.parse(catalogWithAttributes)
        
        // Then
        assertNotNull(result)
        assertTrue(result.artifactId.contains("spring.core"))
        assertEquals("implementation", result.scope)
        
        // Verify attributes
        assertFalse(result.config.attributes.isEmpty())
        assertTrue(result.config.attributes.any { it.key.contains("USAGE_ATTRIBUTE") })
    }
    
    @Test
    fun `should parse complex catalog usage correctly`() {
        // Given
        val complexCatalogUsage = GradleGroovyTest9VersionCatalogs.complexCatalogUsage
        
        // When
        val result = parser.parse(complexCatalogUsage)
        
        // Then
        assertNotNull(result)
        assertTrue(result.artifactId.contains("spring.core"))
        assertEquals("implementation", result.scope)
        
        // Verify various configurations
        assertFalse(result.config.capabilities.isEmpty() && 
                   result.config.attributes.isEmpty() && 
                   result.config.exclusions.isEmpty())
    }
} 