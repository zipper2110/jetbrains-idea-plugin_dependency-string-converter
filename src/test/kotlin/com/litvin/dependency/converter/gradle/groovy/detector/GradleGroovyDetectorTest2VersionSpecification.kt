package com.litvin.dependency.converter.gradle.groovy.detector

import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest2VersionSpecification
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GradleGroovyDetectorTest2VersionSpecification {
    
    private val detector = GradleGroovyDetector()
    
    @Test
    fun `should detect dynamic latest version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.latestVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect plus notation as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.plusNotation
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect latest minor as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.latestMinor
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect version range inclusive as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.versionRangeInclusive
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect version range exclusive as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.versionRangeExclusive
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect version range mixed as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.versionRangeMixed
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect rich version prefer as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionPrefer
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect rich version reject as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionReject
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect rich version require as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionRequire
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect rich version strictly as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionStrictly
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    @Disabled
    fun `should detect rich version combination as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionCombination
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect SNAPSHOT version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.snapshotVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect release candidate version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.rcVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect beta version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.betaVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect alpha version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.alphaVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect milestone version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.milestoneVersion
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
