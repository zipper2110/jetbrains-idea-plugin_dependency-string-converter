package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest8ProjectDependencies
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class DependencyFormatTest8ProjectDependencies {
    
    @Test
    fun `should detect basic project dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.basicProject
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect project dependency with configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithConfiguration
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect project dependency with capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithCapabilities
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect project dependency with attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithAttributes
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect project dependency with exclusions as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithExclusions
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect project dependency without transitive dependencies as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithoutTransitive
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect project dependency with build type as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithBuildType
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect project dependency with configuration and capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithConfigAndCapabilities
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect complex project dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.complexProject
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect multiple project dependencies as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.multipleProjects
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 