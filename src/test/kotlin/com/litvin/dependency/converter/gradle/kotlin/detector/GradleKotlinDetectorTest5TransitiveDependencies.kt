package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest5TransitiveDependencies
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest5TransitiveDependencies {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect single exclusion as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.singleExclusion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple exclusions as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.multipleExclusions

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect group only exclusion as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.groupOnlyExclusion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect module only exclusion as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.moduleOnlyExclusion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect transitive disabled as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.transitiveDisabled

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect capability conflict as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.capabilityConflict

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect dependency substitution as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.dependencySubstitution

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect force version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.forceVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect exclude all transitive dependencies as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.excludeAllTransitive

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect complex combination as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.complexCombination

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect configuration wide exclusions as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.configurationExclusions

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect dependency constraints as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest5TransitiveDependencies.dependencyConstraints

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
