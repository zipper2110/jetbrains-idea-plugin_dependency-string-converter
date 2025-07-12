package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest4Attributes
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest4Attributes {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect basic attribute as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.basicAttribute
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple attributes as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.multipleAttributes

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect custom attribute as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.customAttribute

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect library elements attribute as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.libraryElements

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect bundling attribute as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.bundling

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect documentation type attribute as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.documentationType

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect status attribute as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.status

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect attributes with artifact type as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.attributesWithArtifactType

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect attributes with classifier as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.attributesWithClassifier

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex combination as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest4Attributes.complexCombination

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
