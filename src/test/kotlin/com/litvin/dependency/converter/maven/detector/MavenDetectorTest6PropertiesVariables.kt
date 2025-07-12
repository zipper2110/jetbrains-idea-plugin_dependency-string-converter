package com.litvin.dependency.converter.maven.detector

import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.converter.maven.reference.MavenParserTest6PropertiesVariables
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MavenDetectorTest6PropertiesVariables {

    private val detector = MavenDetector()
    
    @Test
    fun `should detect version property as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.versionPropertyXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect groupId property as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.groupIdPropertyXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect artifactId property as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.artifactIdPropertyXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect multiple properties as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.multiplePropertiesXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect built-in property as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.builtInPropertyXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect scope property as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.scopePropertyXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect classifier property as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.classifierPropertyXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect systemPath property as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.systemPathPropertyXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range property as Maven`() {
        // Given
        val dependency = MavenParserTest6PropertiesVariables.versionRangePropertyXml

        // When
        val isSupported = detector.isInputSupported(dependency)

        // Then
        assertTrue(isSupported)
    }
} 
