package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest8ProjectDependencies
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy project dependency formats
 */
class GradleGroovyDependencyParserTest8ProjectDependencies {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse basic project dependency correctly`() {
        // Given
        val basicProject = GradleGroovyTest8ProjectDependencies.basicProject
        
        // When
        val result = parser.parse(basicProject)
        
        // Then
        assertNotNull(result)
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        
        // Verify project config
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
    }
    
    @Test
    fun `should parse project dependency with configuration correctly`() {
        // Given
        val projectWithConfiguration = GradleGroovyTest8ProjectDependencies.projectWithConfiguration
        
        // When
        val result = parser.parse(projectWithConfiguration)
        
        // Then
        assertNotNull(result)
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        
        // Verify project config
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        assertEquals("default", result.project?.targetConfiguration)
    }
    
    @Test
    fun `should parse project dependency with exclusions correctly`() {
        // Given
        val projectWithExclusions = GradleGroovyTest8ProjectDependencies.projectWithExclusions
        
        // When
        val result = parser.parse(projectWithExclusions)
        
        // Then
        assertNotNull(result)
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        
        // Verify project config
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        
        // Verify exclusions
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
    }
    
    @Test
    fun `should parse project dependency with transitive=false correctly`() {
        // Given
        val projectWithoutTransitive = GradleGroovyTest8ProjectDependencies.projectWithoutTransitive
        
        // When
        val result = parser.parse(projectWithoutTransitive)
        
        // Then
        assertNotNull(result)
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        
        // Verify project config
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        
        // Verify transitive flag
        assertFalse(result.config.transitive)
    }
} 