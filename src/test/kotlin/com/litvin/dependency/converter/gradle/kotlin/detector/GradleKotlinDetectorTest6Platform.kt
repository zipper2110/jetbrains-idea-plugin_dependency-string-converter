package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest6Platform
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest6Platform {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect basic platform as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.basicPlatform
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.platformWithDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple platforms as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.multiplePlatforms

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform enforced version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.platformEnforcedVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with constraints as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.platformWithConstraints

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with capabilities as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.platformWithCapabilities

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with attributes as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.platformWithAttributes

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect custom platform as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.customPlatform

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with exclusions as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.platformWithExclusions

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex platform combination as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest6Platform.complexPlatformCombination

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
