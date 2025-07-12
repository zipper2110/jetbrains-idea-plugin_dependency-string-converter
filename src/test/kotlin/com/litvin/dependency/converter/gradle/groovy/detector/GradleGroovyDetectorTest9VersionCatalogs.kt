package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest9VersionCatalogs
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class GradleGroovyDetectorTest9VersionCatalogs {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect basic version catalog reference as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.basicCatalogReference
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version catalog with configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithConfiguration
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version catalog bundle as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogBundle
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version catalog with version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version catalog with capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithCapabilities
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version catalog with attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithAttributes
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple catalog references as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.multipleCatalogReferences
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect catalog with rich version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithRichVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex catalog usage as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.complexCatalogUsage
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect catalog declaration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogDeclaration
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
