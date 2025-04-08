package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest9Bundles {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse bundle dependency`() {
        // Given
        val input = """
            implementation(platform('com.example:bundle:1.0.0'))
            implementation('com.example:core:1.0.0')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("bundle", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.bundle)
        assertEquals(1, result.bundle?.dependencies?.size)
        val bundleDep = result.bundle?.dependencies?.first()
        assertEquals("com.example", bundleDep?.groupId)
        assertEquals("core", bundleDep?.artifactId)
        assertEquals("1.0.0", bundleDep?.version)
    }

    @Test
    fun `should parse bundle dependency with multiple dependencies`() {
        // Given
        val input = """
            implementation(platform('com.example:bundle:1.0.0'))
            implementation('com.example:core:1.0.0')
            implementation('com.example:utils:1.0.0')
            implementation('com.example:api:1.0.0')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("bundle", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.bundle)
        assertEquals(3, result.bundle?.dependencies?.size)
        
        val deps = result.bundle?.dependencies
        assertEquals("core", deps?.get(0)?.artifactId)
        assertEquals("utils", deps?.get(1)?.artifactId)
        assertEquals("api", deps?.get(2)?.artifactId)
    }

    @Test
    fun `should parse bundle dependency with variant`() {
        // Given
        val input = """
            debugImplementation(platform('com.example:bundle:1.0.0'))
            debugImplementation('com.example:core:1.0.0')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("bundle", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("debugImplementation", result.scope)
        assertNotNull(result.bundle)
        assertEquals(1, result.bundle?.dependencies?.size)
        val bundleDep = result.bundle?.dependencies?.first()
        assertEquals("core", bundleDep?.artifactId)
    }

    @Test
    fun `should parse bundle dependency with configuration`() {
        // Given
        val input = """
            implementation(platform('com.example:bundle:1.0.0'))
            implementation('com.example:core:1.0.0') {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("bundle", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.bundle)
        assertEquals(1, result.bundle?.dependencies?.size)
        val bundleDep = result.bundle?.dependencies?.first()
        assertEquals("core", bundleDep?.artifactId)
        assertFalse(bundleDep?.transitive ?: true)
    }

    @Test
    fun `should parse bundle dependency with mixed configurations`() {
        // Given
        val input = """
            implementation(platform('com.example:bundle:1.0.0'))
            implementation('com.example:core:1.0.0') {
                transitive = false
            }
            implementation('com.example:utils:1.0.0')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("bundle", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.bundle)
        assertEquals(2, result.bundle?.dependencies?.size)
        
        val deps = result.bundle?.dependencies
        assertEquals("core", deps?.get(0)?.artifactId)
        assertFalse(deps?.get(0)?.transitive ?: true)
        assertEquals("utils", deps?.get(1)?.artifactId)
    }
} 