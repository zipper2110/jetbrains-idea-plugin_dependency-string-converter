package com.litvin.dependency

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.reference.MavenDimension7ExclusionsOptional
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse

/**
 * Tests that validate parsing of Maven dependencies with exclusions and optional flags
 */
class MavenDimension7ExclusionsOptionalTest {
    private val parser = MavenDependencyParser()
    
    @Test
    fun testParseSingleExclusion() {
        val result = parser.parse(MavenDimension7ExclusionsOptional.singleExclusionXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(1, result.exclusions.size)
        assertEquals("commons-logging", result.exclusions[0].groupId)
        assertEquals("commons-logging", result.exclusions[0].artifactId)
        assertFalse(result.optional)
    }
    
    @Test
    fun testParseMultipleExclusions() {
        val result = parser.parse(MavenDimension7ExclusionsOptional.multipleExclusionsXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(2, result.exclusions.size)
        
        // First exclusion
        assertEquals("commons-logging", result.exclusions[0].groupId)
        assertEquals("commons-logging", result.exclusions[0].artifactId)
        
        // Second exclusion
        assertEquals("log4j", result.exclusions[1].groupId)
        assertEquals("log4j", result.exclusions[1].artifactId)
        
        assertFalse(result.optional)
    }
    
    @Test
    fun testParseWildcardExclusion() {
        val result = parser.parse(MavenDimension7ExclusionsOptional.wildcardExclusionXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(1, result.exclusions.size)
        assertEquals("commons-logging", result.exclusions[0].groupId)
        assertEquals("*", result.exclusions[0].artifactId)
        assertFalse(result.optional)
    }
    
    @Test
    fun testParseOptionalDependency() {
        val result = parser.parse(MavenDimension7ExclusionsOptional.optionalDependencyXml)
        assertNotNull(result)
        assertEquals("com.h2database", result.groupId)
        assertEquals("h2", result.artifactId)
        assertEquals("2.1.210", result.version)
        assertTrue(result.exclusions.isEmpty())
        assertTrue(result.optional)
    }
    
    @Test
    fun testParseExclusionsAndOptional() {
        val result = parser.parse(MavenDimension7ExclusionsOptional.exclusionsAndOptionalXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-context", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals(1, result.exclusions.size)
        assertEquals("commons-logging", result.exclusions[0].groupId)
        assertEquals("commons-logging", result.exclusions[0].artifactId)
        assertTrue(result.optional)
    }
    
    @Test
    fun testParseExclusionWithScopeAndType() {
        val result = parser.parse(MavenDimension7ExclusionsOptional.exclusionWithScopeAndTypeXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-context", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("testImplementation", result.scope) // 'test' maps to 'testImplementation'
        assertEquals("jar", result.type)
        assertEquals(1, result.exclusions.size)
        assertEquals("commons-logging", result.exclusions[0].groupId)
        assertEquals("commons-logging", result.exclusions[0].artifactId)
        assertFalse(result.optional)
    }
    
    @Test
    fun testParseOptionalWithClassifier() {
        val result = parser.parse(MavenDimension7ExclusionsOptional.optionalWithClassifierXml)
        assertNotNull(result)
        assertEquals("org.postgresql", result.groupId)
        assertEquals("postgresql", result.artifactId)
        assertEquals("42.3.1", result.version)
        assertEquals("jre7", result.classifier)
        assertTrue(result.optional)
        assertTrue(result.exclusions.isEmpty())
    }
} 