package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest3Configurations
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GradleGroovyDetectorTest3Configurations {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect implementation configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.implementation
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect API configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.api
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect compileOnly configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.compileOnly
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect runtimeOnly configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.runtimeOnly
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect testImplementation configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.testImplementation
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect testCompileOnly configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.testCompileOnly
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect testRuntimeOnly configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.testRuntimeOnly
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect annotationProcessor configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.annotationProcessor
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect kapt configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.kapt
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect debug configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.debugImplementation
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect release configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.releaseImplementation
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect custom configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.customConfiguration
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple configurations as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.multipleConfigurations
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
