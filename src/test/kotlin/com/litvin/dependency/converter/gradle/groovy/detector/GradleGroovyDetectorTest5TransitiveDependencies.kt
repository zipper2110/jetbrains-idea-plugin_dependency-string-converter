package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest5TransitiveDependencies
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class GradleGroovyDetectorTest5TransitiveDependencies {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect single exclusion as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.singleExclusion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple exclusions as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.multipleExclusions
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect group-only exclusion as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.groupOnlyExclusion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect module-only exclusion as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.moduleOnlyExclusion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect transitive disabled as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.transitiveDisabled
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability conflict as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.capabilityConflict
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect dependency substitution as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.dependencySubstitution
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect force version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.forceVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect exclude all transitive dependencies as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.excludeAllTransitive
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex combination as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.complexCombination
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect configuration-wide exclusions as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.configurationExclusions
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect dependency constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.dependencyConstraints
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
