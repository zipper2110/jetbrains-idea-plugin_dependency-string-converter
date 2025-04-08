package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest8Modules {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse module dependency`() {
        // Given
        val input = """
            implementation(module('com.example:library:1.0.0'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.module)
    }

    @Test
    fun `should parse module dependency with variant`() {
        // Given
        val input = """
            debugImplementation(module('com.example:library:1.0.0'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("debugImplementation", result.scope)
        assertNotNull(result.module)
    }

    @Test
    fun `should parse module dependency with configuration`() {
        // Given
        val input = """
            implementation(module('com.example:library:1.0.0')) {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.module)
        assertFalse(result.transitive)
    }

    @Test
    fun `should parse module dependency with dependencies`() {
        // Given
        val input = """
            implementation(module('com.example:library:1.0.0')) {
                dependency('com.example:core:1.0.0')
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.module)
        assertEquals(1, result.module?.dependencies?.size)
        val dep = result.module?.dependencies?.first()
        assertEquals("com.example", dep?.groupId)
        assertEquals("core", dep?.artifactId)
        assertEquals("1.0.0", dep?.version)
    }

    @Test
    fun `should parse module dependency with multiple dependencies`() {
        // Given
        val input = """
            implementation(module('com.example:library:1.0.0')) {
                dependency('com.example:core:1.0.0')
                dependency('com.example:utils:1.0.0')
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.module)
        assertEquals(2, result.module?.dependencies?.size)
        val deps = result.module?.dependencies
        assertEquals("core", deps?.get(0)?.artifactId)
        assertEquals("utils", deps?.get(1)?.artifactId)
    }

    @Test
    fun `should parse module dependency with mixed configurations`() {
        // Given
        val input = """
            implementation(module('com.example:library:1.0.0')) {
                transitive = false
                dependency('com.example:core:1.0.0') {
                    transitive = false
                }
                dependency('com.example:utils:1.0.0')
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.module)
        assertFalse(result.transitive)
        assertEquals(2, result.module?.dependencies?.size)
        val deps = result.module?.dependencies
        assertEquals("core", deps?.get(0)?.artifactId)
        assertFalse(deps?.get(0)?.transitive ?: true)
        assertEquals("utils", deps?.get(1)?.artifactId)
    }
} 