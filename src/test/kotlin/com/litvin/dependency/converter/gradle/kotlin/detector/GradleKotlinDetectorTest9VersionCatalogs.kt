package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest9VersionCatalogs
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest9VersionCatalogs {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect basic catalog reference as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.basicCatalogReference
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect catalog with configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.catalogWithConfiguration

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect catalog bundle as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.catalogBundle

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect catalog with version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.catalogWithVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect catalog with capabilities as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.catalogWithCapabilities

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect catalog with attributes as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.catalogWithAttributes

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple catalog references as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.multipleCatalogReferences

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect catalog with rich version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.catalogWithRichVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex catalog usage as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.complexCatalogUsage

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect catalog declaration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest9VersionCatalogs.catalogDeclaration

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
