package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest8ProjectDependencies
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest8ProjectDependencies {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect simple project dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.simpleProject
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect nested project dependency as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.nestedProject

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.projectWithConfiguration

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with build type as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.projectWithBuildType

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with transitive false as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.projectWithTransitiveFalse

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with exclude as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.projectWithExclude

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with multiple excludes as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.projectWithMultipleExcludes

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with capability as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.projectWithCapability

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with attributes as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.projectWithAttributes

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect project dependency with multiple configurations as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest8ProjectDependencies.projectWithMultipleConfigurations

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
