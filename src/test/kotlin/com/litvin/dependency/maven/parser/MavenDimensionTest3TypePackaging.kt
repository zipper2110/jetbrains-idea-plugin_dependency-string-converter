package com.litvin.dependency.maven.parser

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.maven.reference.MavenParserTest3TypePackaging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of various type/packaging formats from MavenDimension3TypePackaging
 */
class MavenDimensionTest3TypePackaging {
    private val parser = MavenDependencyParser()
    
    @Test
    fun testParseDefaultType() {
        val result = parser.parse(MavenParserTest3TypePackaging.defaultTypeXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("jar", result.type)
    }
    
    @Test
    fun testParseJarType() {
        val result = parser.parse(MavenParserTest3TypePackaging.jarTypeXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("jar", result.type)
    }
    
    @Test
    fun testParseWarType() {
        val result = parser.parse(MavenParserTest3TypePackaging.warTypeXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("sample-webapp", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("war", result.type)
    }
    
    @Test
    fun testParseEarType() {
        val result = parser.parse(MavenParserTest3TypePackaging.earTypeXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("enterprise-app", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("ear", result.type)
    }
    
    @Test
    fun testParsePomType() {
        val result = parser.parse(MavenParserTest3TypePackaging.pomTypeXml)
        assertNotNull(result)
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-dependencies", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("pom", result.type)
    }
    
    @Test
    fun testParseMavenPluginType() {
        val result = parser.parse(MavenParserTest3TypePackaging.mavenPluginTypeXml)
        assertNotNull(result)
        assertEquals("org.apache.maven.plugins", result.groupId)
        assertEquals("maven-compiler-plugin", result.artifactId)
        assertEquals("3.10.1", result.version)
        assertEquals("maven-plugin", result.type)
    }
    
    @Test
    fun testParseTestJarType() {
        val result = parser.parse(MavenParserTest3TypePackaging.testJarTypeXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("library-with-test-utils", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("test-jar", result.type)
    }
    
    @Test
    fun testParseEjbType() {
        val result = parser.parse(MavenParserTest3TypePackaging.ejbTypeXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("enterprise-bean", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("ejb", result.type)
    }
    
    @Test
    fun testParseBundleType() {
        val result = parser.parse(MavenParserTest3TypePackaging.bundleTypeXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("osgi-bundle", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("bundle", result.type)
    }
    
    @Test
    fun testParseZipType() {
        val result = parser.parse(MavenParserTest3TypePackaging.zipTypeXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("resources-package", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("zip", result.type)
    }
} 