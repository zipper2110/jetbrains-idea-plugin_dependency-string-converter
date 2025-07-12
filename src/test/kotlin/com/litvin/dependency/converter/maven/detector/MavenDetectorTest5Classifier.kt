package com.litvin.dependency.converter.maven.detector

import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.converter.maven.reference.MavenParserTest5Classifier
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MavenDetectorTest5Classifier {

    private val detector = MavenDetector()
    
    @Test
    fun `should detect no classifier as Maven`() {
        // Given
        val dependency = MavenParserTest5Classifier.noClassifierXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect sources classifier as Maven`() {
        // Given
        val dependency = MavenParserTest5Classifier.sourcesClassifierXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect javadoc classifier as Maven`() {
        // Given
        val dependency = MavenParserTest5Classifier.javadocClassifierXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect tests classifier as Maven`() {
        // Given
        val dependency = MavenParserTest5Classifier.testsClassifierXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect native windows classifier as Maven`() {
        // Given
        val dependency = MavenParserTest5Classifier.nativeWindowsClassifierXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect jdk11 classifier as Maven`() {
        // Given
        val dependency = MavenParserTest5Classifier.jdk11ClassifierXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect javadoc with type as Maven`() {
        // Given
        val dependency = MavenParserTest5Classifier.javadocWithTypeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect sources with scope as Maven`() {
        // Given
        val dependency = MavenParserTest5Classifier.sourcesWithScopeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
