package com.litvin.dependency

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.reference.MavenDimension2VersionSpecification
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of various version specification formats from MavenDimension2VersionSpecification
 */
class MavenDimension2VersionSpecificationTest {
    private val parser = MavenDependencyParser()
    
    @Test
    fun testParseExactVersion() {
        val result = parser.parse(MavenDimension2VersionSpecification.exactVersionXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
    }
    
    @Test
    fun testParseSnapshotVersion() {
        val result = parser.parse(MavenDimension2VersionSpecification.snapshotVersionXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0-SNAPSHOT", result.version)
    }
    
    @Test
    fun testParseQualifierVersion() {
        val result = parser.parse(MavenDimension2VersionSpecification.qualifierVersionXml)
        assertNotNull(result)
        assertEquals("org.apache.tomcat", result.groupId)
        assertEquals("tomcat-catalina", result.artifactId)
        assertEquals("9.0.56-beta", result.version)
    }
    
    @Test
    fun testParseTimestampVersion() {
        val result = parser.parse(MavenDimension2VersionSpecification.timestampVersionXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0-20220131.131415-42", result.version)
    }
    
    @Test
    fun testParseVersionRangeInclusiveExclusive() {
        val result = parser.parse(MavenDimension2VersionSpecification.versionRangeInclusiveExclusiveXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[1.0.0,2.0.0)", result.version)
    }
    
    @Test
    fun testParseVersionRangeInclusiveInclusive() {
        val result = parser.parse(MavenDimension2VersionSpecification.versionRangeInclusiveInclusiveXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[1.0.0,2.0.0]", result.version)
    }
    
    @Test
    fun testParseVersionRangeExclusiveInclusive() {
        val result = parser.parse(MavenDimension2VersionSpecification.versionRangeExclusiveInclusiveXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("(1.0.0,2.0.0]", result.version)
    }
    
    @Test
    fun testParseVersionRangeGreaterThanOrEqual() {
        val result = parser.parse(MavenDimension2VersionSpecification.versionRangeGreaterThanOrEqualXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("[1.0.0,)", result.version)
    }
    
    @Test
    fun testParseVersionRangeLessThanOrEqual() {
        val result = parser.parse(MavenDimension2VersionSpecification.versionRangeLessThanOrEqualXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("(,2.0.0]", result.version)
    }
    
    @Test
    fun testParseLatestVersion() {
        val result = parser.parse(MavenDimension2VersionSpecification.latestVersionXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("LATEST", result.version)
    }
    
    @Test
    fun testParseReleaseVersion() {
        val result = parser.parse(MavenDimension2VersionSpecification.releaseVersionXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("RELEASE", result.version)
    }
} 