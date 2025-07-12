package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest10ArtifactOnly
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class GradleGroovyDetectorTest10ArtifactOnly {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect single file dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.singleFile
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect file tree dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.fileTree
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect file tree with multiple patterns as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.fileTreeMultiplePatterns
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect custom artifact as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.customArtifact
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple artifacts as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.multipleArtifacts
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect directory dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.directory
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect URL dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.url
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect artifact with configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.artifactWithConfiguration
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex artifact combination as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.complexArtifact
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
