package com.litvin.dependency.converter.maven.detector

import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.converter.maven.reference.MavenParserTest7ExclusionsOptional
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MavenDetectorTest7ExclusionsOptional {

    private val detector = MavenDetector()
    
    @Test
    fun `should detect single exclusion as Maven`() {
        // Given
        val dependency = MavenParserTest7ExclusionsOptional.singleExclusionXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple exclusions as Maven`() {
        // Given
        val dependency = MavenParserTest7ExclusionsOptional.multipleExclusionsXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect wildcard exclusion as Maven`() {
        // Given
        val dependency = MavenParserTest7ExclusionsOptional.wildcardExclusionXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect optional dependency as Maven`() {
        // Given
        val dependency = MavenParserTest7ExclusionsOptional.optionalDependencyXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect exclusions and optional as Maven`() {
        // Given
        val dependency = MavenParserTest7ExclusionsOptional.exclusionsAndOptionalXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect exclusion with scope and type as Maven`() {
        // Given
        val dependency = MavenParserTest7ExclusionsOptional.exclusionWithScopeAndTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect optional with classifier as Maven`() {
        // Given
        val dependency = MavenParserTest7ExclusionsOptional.optionalWithClassifierXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
