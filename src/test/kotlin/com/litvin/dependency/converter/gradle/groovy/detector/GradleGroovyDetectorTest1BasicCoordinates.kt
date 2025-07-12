package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest1BasicCoordinates
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleGroovyDetectorTest1BasicCoordinates {

    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect basic string notation as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.basicStringNotation
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect map notation as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.mapNotation

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect API dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.apiDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect compileOnly dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.compileOnlyDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect dependency without version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.noVersionDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect company module dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.companyModuleDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect Spring Boot starter dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.springBootStarterDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect Quarkus dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.quarkusDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
