package com.litvin.dependency.converter.maven.detector

import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.converter.maven.reference.MavenParserTest4Scope
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MavenDetectorTest4Scope {

    private val detector = MavenDetector()
    
    @Test
    fun `should detect default scope as Maven`() {
        // Given
        val dependency = MavenParserTest4Scope.defaultScopeXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect compile scope as Maven`() {
        // Given
        val dependency = MavenParserTest4Scope.compileScopeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect runtime scope as Maven`() {
        // Given
        val dependency = MavenParserTest4Scope.runtimeScopeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect test scope as Maven`() {
        // Given
        val dependency = MavenParserTest4Scope.testScopeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect provided scope as Maven`() {
        // Given
        val dependency = MavenParserTest4Scope.providedScopeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect system scope as Maven`() {
        // Given
        val dependency = MavenParserTest4Scope.systemScopeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect import scope as Maven`() {
        // Given
        val dependency = MavenParserTest4Scope.importScopeXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
