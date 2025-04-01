package com.litvin.dependency

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.maven.reference.MavenDimension4Scope
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Tests that validate parsing of various scope formats from MavenDimension4Scope
 */
class MavenDimension4ScopeTest {
    private val parser = MavenDependencyParser()
    
    @Test
    fun testParseDefaultScope() {
        val result = parser.parse(MavenDimension4Scope.defaultScopeXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope) // Default scope should be implementation
    }
    
    @Test
    fun testParseCompileScope() {
        val result = parser.parse(MavenDimension4Scope.compileScopeXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope) // 'compile' maps to 'implementation'
    }
    
    @Test
    fun testParseRuntimeScope() {
        val result = parser.parse(MavenDimension4Scope.runtimeScopeXml)
        assertNotNull(result)
        assertEquals("org.postgresql", result.groupId)
        assertEquals("postgresql", result.artifactId)
        assertEquals("42.3.1", result.version)
        assertEquals("runtimeOnly", result.scope) // 'runtime' maps to 'runtimeOnly'
    }
    
    @Test
    fun testParseTestScope() {
        val result = parser.parse(MavenDimension4Scope.testScopeXml)
        assertNotNull(result)
        assertEquals("junit", result.groupId)
        assertEquals("junit", result.artifactId)
        assertEquals("4.13.2", result.version)
        assertEquals("testImplementation", result.scope) // 'test' maps to 'testImplementation'
    }
    
    @Test
    fun testParseProvidedScope() {
        val result = parser.parse(MavenDimension4Scope.providedScopeXml)
        assertNotNull(result)
        assertEquals("javax.servlet", result.groupId)
        assertEquals("javax.servlet-api", result.artifactId)
        assertEquals("4.0.1", result.version)
        assertEquals("compileOnly", result.scope) // 'provided' maps to 'compileOnly'
    }
    
    @Test
    fun testParseSystemScope() {
        val result = parser.parse(MavenDimension4Scope.systemScopeXml)
        assertNotNull(result)
        assertEquals("com.oracle", result.groupId)
        assertEquals("ojdbc8", result.artifactId)
        assertEquals("19.3.0", result.version)
        assertEquals("implementation", result.scope) // 'system' maps to 'implementation'
        assertEquals("\${basedir}/lib/ojdbc8.jar", result.systemPath) // Check systemPath
    }
    
    // Note: Import scope test is special because it's in a different XML structure
    // and might require separate handling. For now, we're not testing it.
} 