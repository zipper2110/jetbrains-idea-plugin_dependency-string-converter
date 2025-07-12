package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest7Capabilities
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest7Capabilities {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect basic capability as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.basicCapability
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple capabilities as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.multipleCapabilities

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.capabilityWithVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with reason as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.capabilityWithReason

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with attributes as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.capabilityWithAttributes

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with version constraints as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.capabilityWithVersionConstraints

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect optional capability as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.optionalCapability

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.capabilityWithConfiguration

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex capability combination as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest7Capabilities.complexCapabilityCombination

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
