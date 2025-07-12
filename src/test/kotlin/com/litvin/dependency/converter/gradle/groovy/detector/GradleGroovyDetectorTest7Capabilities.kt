package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest7Capabilities
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class GradleGroovyDetectorTest7Capabilities {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect basic capability as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.basicCapability
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.multipleCapabilities
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with reason as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithReason
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithAttributes
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with version constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithVersionConstraints
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect optional capability as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.optionalCapability
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability with configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithConfiguration
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex capability combination as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.complexCapabilityCombination
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
