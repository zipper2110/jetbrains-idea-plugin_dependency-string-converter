package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest8ProjectDependencies
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class GradleGroovyDetectorTest8ProjectDependencies {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect basic project dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.basicProject
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithConfiguration
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithCapabilities
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithAttributes
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with exclusions as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithExclusions
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency without transitive dependencies as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithoutTransitive
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with build type as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithBuildType
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with configuration and capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.projectWithConfigAndCapabilities
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex project dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.complexProject
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple project dependencies as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest8ProjectDependencies.multipleProjects
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
