package com.litvin.dependency.maven.parser

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.maven.reference.MavenDimension1Coordinates
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of various Maven coordinate formats from MavenDimension1Coordinates
 */
class MavenDimension1CoordinatesTest {
    private val parser = MavenDependencyParser()
    
    @Test
    fun testParseSpringCoreXml() {
        val result = parser.parse(MavenDimension1Coordinates.springCoreXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun testParseGuavaXml() {
        val result = parser.parse(MavenDimension1Coordinates.guavaXml)
        assertNotNull(result)
        assertEquals("com.google.guava", result.groupId)
        assertEquals("guava", result.artifactId)
        assertEquals("31.1-jre", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun testParseCommonsLangXml() {
        val result = parser.parse(MavenDimension1Coordinates.commonsLangXml)
        assertNotNull(result)
        assertEquals("org.apache.commons", result.groupId)
        assertEquals("commons-lang3", result.artifactId)
        assertEquals("3.12.0", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun testParseCompanyModuleXml() {
        val result = parser.parse(MavenDimension1Coordinates.companyModuleXml)
        assertNotNull(result)
        assertEquals("com.mycompany.project", result.groupId)
        assertEquals("module-name", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun testParseSpringBootStarterXml() {
        val result = parser.parse(MavenDimension1Coordinates.springBootStarterXml)
        assertNotNull(result)
        assertEquals("org.springframework.boot", result.groupId)
        assertEquals("spring-boot-starter", result.artifactId)
        assertEquals("2.6.3", result.version)
        assertEquals("implementation", result.scope)
    }
    
    @Test
    fun testParseQuarkusCoreXml() {
        val result = parser.parse(MavenDimension1Coordinates.quarkusCoreXml)
        assertNotNull(result)
        assertEquals("io.quarkus", result.groupId)
        assertEquals("quarkus-core", result.artifactId)
        assertEquals("2.7.5.Final", result.version)
        assertEquals("implementation", result.scope)
    }
} 