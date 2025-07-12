package com.litvin.dependency.converter.maven.detector

import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.converter.maven.reference.MavenParserTest3TypePackaging
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MavenDetectorTest3TypePackaging {

    private val detector = MavenDetector()
    
    @Test
    fun `should detect default type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.defaultTypeXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect jar type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.jarTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect war type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.warTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect ear type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.earTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect pom type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.pomTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect maven plugin type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.mavenPluginTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect test jar type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.testJarTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect ejb type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.ejbTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect bundle type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.bundleTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect zip type as Maven`() {
        // Given
        val dependency = MavenParserTest3TypePackaging.zipTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
