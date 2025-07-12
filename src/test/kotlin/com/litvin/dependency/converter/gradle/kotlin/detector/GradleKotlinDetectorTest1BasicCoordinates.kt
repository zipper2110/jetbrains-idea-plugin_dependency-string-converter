package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest1BasicCoordinates
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest1BasicCoordinates {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect basic string notation as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest1BasicCoordinates.basicStringNotation
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect group module version notation as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest1BasicCoordinates.groupModuleVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect API dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest1BasicCoordinates.apiDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect compileOnly dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest1BasicCoordinates.compileOnlyDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect dependency without version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest1BasicCoordinates.noVersionDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect company module dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest1BasicCoordinates.companyModuleDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect Spring Boot starter dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest1BasicCoordinates.springBootStarterDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect Quarkus dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest1BasicCoordinates.quarkusDependency

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
