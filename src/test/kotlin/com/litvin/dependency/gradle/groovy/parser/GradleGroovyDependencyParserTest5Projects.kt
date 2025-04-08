package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.AttributeModel
import com.litvin.dependency.model.CapabilityModel
import com.litvin.dependency.model.ExclusionModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest5Projects {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse simple project dependency`() {
        // Given
        val input = """
            implementation(project(':core'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":core", result.projectPath)
    }

    @Test
    fun `should parse nested project dependency`() {
        // Given
        val input = """
            implementation(project(':feature:auth'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("auth", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":feature:auth", result.projectPath)
    }

    @Test
    fun `should parse project dependency with configuration`() {
        // Given
        val input = """
            implementation(project(path: ':core', configuration: 'api'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":core", result.projectPath)
        assertEquals("api", result.targetConfiguration)
    }

    @Test
    fun `should parse project dependency with build type`() {
        // Given
        val input = """
            implementation(project(path: ':core', configuration: 'debug'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":core", result.projectPath)
        assertEquals("debug", result.targetConfiguration)
    }

    @Test
    fun `should parse project dependency with transitive false`() {
        // Given
        val input = """
            implementation(project(':core')) {
                transitive = false
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":core", result.projectPath)
        assertFalse(result.transitive)
    }

    @Test
    fun `should parse project dependency with exclude`() {
        // Given
        val input = """
            implementation(project(':core')) {
                exclude group: 'org.example', module: 'excluded'
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":core", result.projectPath)
        assertEquals(1, result.exclusions.size)
        assertEquals("org.example", result.exclusions[0].groupId)
        assertEquals("excluded", result.exclusions[0].artifactId)
    }

    @Test
    fun `should parse project dependency with capability`() {
        // Given
        val input = """
            implementation(project(':core')) {
                capabilities {
                    requireCapability('org.example:feature:1.0.0')
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":core", result.projectPath)
        assertEquals(1, result.capabilities.size)
        assertEquals("org.example", result.capabilities[0].group)
        assertEquals("feature", result.capabilities[0].name)
        assertEquals("1.0.0", result.capabilities[0].version)
    }

    @Test
    fun `should parse project dependency with attributes`() {
        // Given
        val input = """
            implementation(project(':core')) {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage, Usage.JAVA_RUNTIME))
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertTrue(result.isProject)
        assertEquals(":core", result.projectPath)
        assertEquals(1, result.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.attributes[0].key)
        assertEquals("Usage.JAVA_RUNTIME", result.attributes[0].value)
    }
} 