package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest10ArtifactOnly
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest10ArtifactOnly {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect single file dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.singleFile
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect file tree dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.fileTree

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect file tree with multiple patterns as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.fileTreeMultiplePatterns

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect custom artifact as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.customArtifact

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple artifacts as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.multipleArtifacts

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect directory dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.directory

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect URL dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.url

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect artifact with configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.artifactWithConfiguration

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex artifact combination as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest10ArtifactOnly.complexArtifact

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
