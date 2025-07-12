package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest3Configurations
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest3Configurations {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect implementation configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.implementation
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect API configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.api

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect compileOnly configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.compileOnly

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect runtimeOnly configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.runtimeOnly

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect testImplementation configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.testImplementation

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect testCompileOnly configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.testCompileOnly

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect testRuntimeOnly configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.testRuntimeOnly

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect annotationProcessor configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.annotationProcessor

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect kapt configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.kapt

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect debugImplementation configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.debugImplementation

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect releaseImplementation configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.releaseImplementation

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect custom configuration as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.customConfiguration

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple configurations as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest3Configurations.multipleConfigurations

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
