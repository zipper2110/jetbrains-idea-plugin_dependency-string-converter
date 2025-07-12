package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest6Platform
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class GradleGroovyDetectorTest6Platform {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect basic platform dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.basicPlatform
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect enforced platform dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.enforcedPlatform
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithConstraints
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with exclusions as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithExclusions
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithCapabilities
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect custom platform as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.customPlatform
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with dependency constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithDependencyConstraints
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with rich version constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithRichVersions
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect platform with attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithAttributes
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple platforms as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.multiplePlatforms
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
