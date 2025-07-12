package com.litvin.dependency.converter.maven.detector

import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.converter.maven.reference.MavenParserTest2VersionSpecification
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MavenDetectorTest2VersionSpecification {
    
    private val detector = MavenDetector()
    
    @Test
    fun `should detect exact version as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.exactVersionXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect snapshot version as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.snapshotVersionXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect qualifier version as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.qualifierVersionXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect timestamp version as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.timestampVersionXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range inclusive exclusive as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.versionRangeInclusiveExclusiveXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range inclusive inclusive as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.versionRangeInclusiveInclusiveXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range exclusive inclusive as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.versionRangeExclusiveInclusiveXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range greater than or equal as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.versionRangeGreaterThanOrEqualXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect version range less than or equal as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.versionRangeLessThanOrEqualXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect latest version as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.latestVersionXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
    
    @Test
    fun `should detect release version as Maven`() {
        // Given
        val dependency = MavenParserTest2VersionSpecification.releaseVersionXml
        
        // When
        val isSupported = detector.isInputSupported(dependency)
        
        // Then
        assertTrue(isSupported)
    }
} 
