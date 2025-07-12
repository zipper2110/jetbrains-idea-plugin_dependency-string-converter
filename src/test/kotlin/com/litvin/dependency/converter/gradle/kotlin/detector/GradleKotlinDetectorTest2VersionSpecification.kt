package com.litvin.dependency.converter.gradle.kotlin.detector

import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest2VersionSpecification
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleKotlinDetectorTest2VersionSpecification {

    private val detector = GradleKotlinDetector()
    
    @Test
    fun `should detect latest version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.latestVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect plus notation as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.plusNotation

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect latest minor as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.latestMinor

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range inclusive as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.versionRangeInclusive

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range exclusive as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.versionRangeExclusive

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range mixed as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.versionRangeMixed

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect rich version prefer as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.richVersionPrefer

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect rich version reject as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.richVersionReject

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect rich version require as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.richVersionRequire

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect rich version strictly as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.richVersionStrictly

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect rich version combination as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.richVersionCombination

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect snapshot version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.snapshotVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect release candidate version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.rcVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect beta version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.betaVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect alpha version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.alphaVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect milestone version as Gradle Kotlin`() {
        // Given
        val dependency = GradleKotlinTest2VersionSpecification.milestoneVersion

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
