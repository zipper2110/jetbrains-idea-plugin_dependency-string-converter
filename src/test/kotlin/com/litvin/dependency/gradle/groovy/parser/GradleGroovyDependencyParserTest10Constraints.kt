package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest10Constraints {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse dependency constraint`() {
        // Given
        val input = """
            constraints {
                implementation('com.example:library:1.0.0')
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("constraints", result.scope)
        assertNotNull(result.constraint)
    }

    @Test
    fun `should parse dependency constraint with version range`() {
        // Given
        val input = """
            constraints {
                implementation('com.example:library') {
                    version {
                        strictly '[1.0.0,2.0.0)'
                    }
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("[1.0.0,2.0.0)", result.version)
        assertEquals("constraints", result.scope)
        assertNotNull(result.constraint)
    }

    @Test
    fun `should parse dependency constraint with reject version`() {
        // Given
        val input = """
            constraints {
                implementation('com.example:library:1.0.0') {
                    reject('1.1.0', '1.2.0')
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("constraints", result.scope)
        assertNotNull(result.constraint)
        assertEquals(listOf("1.1.0", "1.2.0"), result.constraint?.rejectVersions)
    }

    @Test
    fun `should parse dependency constraint with reason`() {
        // Given
        val input = """
            constraints {
                implementation('com.example:library:1.0.0') {
                    because 'Security vulnerability in older versions'
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("constraints", result.scope)
        assertNotNull(result.constraint)
        assertEquals("Security vulnerability in older versions", result.constraint?.reason)
    }

    @Test
    fun `should parse dependency constraint with variant`() {
        // Given
        val input = """
            debugConstraints {
                implementation('com.example:library:1.0.0')
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("debugConstraints", result.scope)
        assertNotNull(result.constraint)
    }

    @Test
    fun `should parse dependency constraint with multiple configurations`() {
        // Given
        val input = """
            constraints {
                implementation('com.example:library:1.0.0') {
                    reject('1.1.0')
                    because 'Security fix'
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("com.example", result.groupId)
        assertEquals("library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("constraints", result.scope)
        assertNotNull(result.constraint)
        assertEquals(listOf("1.1.0"), result.constraint?.rejectVersions)
        assertEquals("Security fix", result.constraint?.reason)
    }
} 