package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest8Projects {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse project dependency`() {
        // Given
        val input = """
            implementation project(':other-module')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
    }

    @Test
    fun `should parse project dependency with configuration`() {
        // Given
        val input = """
            implementation project(path: ':other-module', configuration: 'default')
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        assertEquals("default", result.config.targetConfiguration)
    }

    @Test
    fun `should parse project dependency with capabilities`() {
        // Given
        val input = """
            implementation(project(':other-module')) {
                capabilities {
                    requireCapability('org.example:feature:1.0')
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        assertEquals(1, result.config.capabilities.size)
        val capability = result.config.capabilities.first()
        assertEquals("org.example", capability.group)
        assertEquals("feature", capability.name)
        assertEquals("1.0", capability.version)
    }

    @Test
    fun `should parse project dependency with attributes`() {
        // Given
        val input = """
            implementation(project(':other-module')) {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        assertEquals(1, result.config.attributes.size)
        val attribute = result.config.attributes.first()
        assertEquals("Usage.USAGE_ATTRIBUTE", attribute.key)
        assertEquals("java-runtime", attribute.value)
    }

    @Test
    fun `should parse project dependency with exclusions`() {
        // Given
        val input = """
            implementation(project(':other-module')) {
                exclude group: 'commons-logging', module: 'commons-logging'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        assertEquals(1, result.config.exclusions.size)
        val exclusion = result.config.exclusions.first()
        assertEquals("commons-logging", exclusion.groupId)
        assertEquals("commons-logging", exclusion.artifactId)
    }

    @Test
    fun `should parse project dependency with transitive = false`() {
        // Given
        val input = """
            implementation(project(':other-module')) {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        assertFalse(result.config.transitive)
    }

    @Test
    fun `should parse project dependency with multiple configurations`() {
        // Given
        val input = """
            implementation(project(path: ':other-module', configuration: 'default')) {
                capabilities {
                    requireCapability('org.example:feature1:1.0')
                    requireCapability('org.example:feature2:1.0')
                }
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
                    attribute(Category.CATEGORY_ATTRIBUTE, 'library')
                }
                exclude group: 'commons-logging'
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals(":other-module", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":other-module", result.project?.path)
        assertEquals("default", result.config.targetConfiguration)
        assertEquals(2, result.config.capabilities.size)
        assertEquals("feature1", result.config.capabilities[0].name)
        assertEquals("feature2", result.config.capabilities[1].name)
        assertEquals(2, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("Category.CATEGORY_ATTRIBUTE", result.config.attributes[1].key)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertFalse(result.config.transitive)
    }
} 