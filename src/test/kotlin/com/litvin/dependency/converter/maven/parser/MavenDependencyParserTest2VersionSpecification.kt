package com.litvin.dependency.converter.maven.parser

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.converter.maven.reference.MavenParserTest2VersionSpecification
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of various version specification formats from MavenDimension2VersionSpecification
 */
class MavenDependencyParserTest2VersionSpecification {
    private val parser = MavenDependencyParser()
    
    @Test
    fun `should parse dependency with exact version`() {
        // Given
        val exactVersionXml = MavenParserTest2VersionSpecification.exactVersionXml
        
        // When
        val result = parser.parse(exactVersionXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
    }
    
    @Test
    fun `should parse dependency with snapshot version`() {
        // Given
        val snapshotVersionXml = MavenParserTest2VersionSpecification.snapshotVersionXml
        
        // When
        val result = parser.parse(snapshotVersionXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0-SNAPSHOT", result.version)
    }
    
    @Test
    fun `should parse dependency with qualifier version`() {
        // Given
        val qualifierVersionXml = MavenParserTest2VersionSpecification.qualifierVersionXml
        
        // When
        val result = parser.parse(qualifierVersionXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.apache.tomcat", result.groupId)
        assertEquals("tomcat-catalina", result.artifactId)
        assertEquals("9.0.56-beta", result.version)
    }
    
    @Test
    fun `should parse dependency with timestamp version`() {
        // Given
        val timestampVersionXml = MavenParserTest2VersionSpecification.timestampVersionXml
        
        // When
        val result = parser.parse(timestampVersionXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0-20220131.131415-42", result.version)
    }
    
    @Test
    fun `should parse dependency with version range inclusive exclusive`() {
        // Given
        val versionRangeXml = MavenParserTest2VersionSpecification.versionRangeInclusiveExclusiveXml
        
        // When
        val result = parser.parse(versionRangeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[1.0.0,2.0.0)", result.version)
    }
    
    @Test
    fun `should parse dependency with version range inclusive inclusive`() {
        // Given
        val versionRangeXml = MavenParserTest2VersionSpecification.versionRangeInclusiveInclusiveXml
        
        // When
        val result = parser.parse(versionRangeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[1.0.0,2.0.0]", result.version)
    }
    
    @Test
    fun `should parse dependency with version range exclusive inclusive`() {
        // Given
        val versionRangeXml = MavenParserTest2VersionSpecification.versionRangeExclusiveInclusiveXml
        
        // When
        val result = parser.parse(versionRangeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("(1.0.0,2.0.0]", result.version)
    }
    
    @Test
    fun `should parse dependency with version range greater than or equal`() {
        // Given
        val versionRangeXml = MavenParserTest2VersionSpecification.versionRangeGreaterThanOrEqualXml
        
        // When
        val result = parser.parse(versionRangeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[1.0.0,)", result.version)
    }
    
    @Test
    fun `should parse dependency with version range less than or equal`() {
        // Given
        val versionRangeXml = MavenParserTest2VersionSpecification.versionRangeLessThanOrEqualXml
        
        // When
        val result = parser.parse(versionRangeXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("(,2.0.0]", result.version)
    }
    
    @Test
    fun `should parse dependency with latest version`() {
        // Given
        val latestVersionXml = MavenParserTest2VersionSpecification.latestVersionXml
        
        // When
        val result = parser.parse(latestVersionXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("LATEST", result.version)
    }
    
    @Test
    fun `should parse dependency with release version`() {
        // Given
        val releaseVersionXml = MavenParserTest2VersionSpecification.releaseVersionXml
        
        // When
        val result = parser.parse(releaseVersionXml)
        
        // Then
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("RELEASE", result.version)
    }
} 