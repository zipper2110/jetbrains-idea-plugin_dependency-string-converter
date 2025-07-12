package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest4Attributes
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class GradleGroovyDetectorTest4Attributes {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect basic attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.basicAttribute
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.multipleAttributes
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect custom attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.customAttribute
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect library elements attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.libraryElements
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect bundling attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.bundling
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect documentation type attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.documentationType
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect status attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.status
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect attributes with artifact type as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.attributesWithArtifactType
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect attributes with classifier as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.attributesWithClassifier
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex combination of attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.complexCombination
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
