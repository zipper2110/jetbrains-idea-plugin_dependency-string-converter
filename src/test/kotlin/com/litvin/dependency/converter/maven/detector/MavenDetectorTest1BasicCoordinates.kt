package com.litvin.dependency.converter.maven.detector

import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.converter.maven.reference.MavenParserTest1Coordinates
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MavenDetectorTest1BasicCoordinates {

    private val detector = MavenDetector()
    
    @Test
    fun `should detect Spring Core XML as Maven`() {
        // Given
        val dependency = MavenParserTest1Coordinates.springCoreXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect Guava XML as Maven`() {
        // Given
        val dependency = MavenParserTest1Coordinates.guavaXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect Commons Lang XML as Maven`() {
        // Given
        val dependency = MavenParserTest1Coordinates.commonsLangXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect company module XML as Maven`() {
        // Given
        val dependency = MavenParserTest1Coordinates.companyModuleXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect Spring Boot starter XML as Maven`() {
        // Given
        val dependency = MavenParserTest1Coordinates.springBootStarterXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect Quarkus core XML as Maven`() {
        // Given
        val dependency = MavenParserTest1Coordinates.quarkusCoreXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
