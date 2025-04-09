package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest5TransitiveDependencies
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy transitive dependency formats
 */
class GradleGroovyDependencyParserTest5TransitiveDependencies {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse dependency with single exclusion correctly`() {
        // Given
        val singleExclusion = GradleGroovyTest5TransitiveDependencies.singleExclusion
        
        // When
        val result = parser.parse(singleExclusion)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify exclusions
        assertFalse(result.config.exclusions.isEmpty())
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
    }
    
    @Test
    fun `should parse dependency with multiple exclusions correctly`() {
        // Given
        val multipleExclusions = GradleGroovyTest5TransitiveDependencies.multipleExclusions
        
        // When
        val result = parser.parse(multipleExclusions)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify exclusions
        assertFalse(result.config.exclusions.isEmpty())
        assertEquals(2, result.config.exclusions.size)
        
        // Check that both exclusions are present
        assertTrue(result.config.exclusions.any { it.groupId == "commons-logging" && it.artifactId == "commons-logging" })
        assertTrue(result.config.exclusions.any { it.groupId == "log4j" && it.artifactId == "log4j" })
    }
    
    @Test
    fun `should parse dependency with group-only exclusion correctly`() {
        // Given
        val groupOnlyExclusion = GradleGroovyTest5TransitiveDependencies.groupOnlyExclusion
        
        // When
        val result = parser.parse(groupOnlyExclusion)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify exclusions
        assertFalse(result.config.exclusions.isEmpty())
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
    }
    
    @Test
    fun `should parse dependency with transitive=false correctly`() {
        // Given
        val transitiveDisabled = GradleGroovyTest5TransitiveDependencies.transitiveDisabled
        
        // When
        val result = parser.parse(transitiveDisabled)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify transitive
        assertFalse(result.config.transitive)
    }
    
    @Test
    fun `should parse dependency with force=true correctly`() {
        // Given
        val forceVersion = GradleGroovyTest5TransitiveDependencies.forceVersion
        
        // When
        val result = parser.parse(forceVersion)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify force
        assertTrue(result.config.force)
    }
    
    @Test
    fun `should parse complex combination correctly`() {
        // Given
        val complexCombination = GradleGroovyTest5TransitiveDependencies.complexCombination
        
        // When
        val result = parser.parse(complexCombination)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify transitive settings
        assertTrue(result.config.force)
        
        // Verify exclusions
        assertFalse(result.config.exclusions.isEmpty())
        assertTrue(result.config.exclusions.any { it.groupId == "commons-logging" && it.artifactId == "commons-logging" })
        assertTrue(result.config.exclusions.any { it.groupId == "log4j" })
        
        // Verify capabilities
        assertFalse(result.config.capabilities.isEmpty())
        assertTrue(result.config.capabilities.any { 
            it.group == "org.springframework" && it.name == "spring-core-capability" 
        })
    }
} 