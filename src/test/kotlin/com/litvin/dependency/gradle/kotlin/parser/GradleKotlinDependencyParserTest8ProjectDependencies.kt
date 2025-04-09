package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest8ProjectDependencies {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse basic project dependency`() {
        // Given
        val input = """
            implementation(project(":core"))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":core", result.project?.path)
    }

    @Test
    fun `should parse project dependency with target configuration`() {
        // Given
        val input = """
            implementation(project(":core", configuration = "default"))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":core", result.project?.path)
        assertEquals("default", result.project?.targetConfiguration)
    }

    @Test
    fun `should parse project dependency with capabilities`() {
        // Given
        val input = """
            implementation(project(":core")) {
                capabilities {
                    requireCapability("org.example:feature:1.0")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":core", result.project?.path)
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.example", result.config.capabilities[0].group)
        assertEquals("feature", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
    }

    @Test
    fun `should parse project dependency with attributes`() {
        // Given
        val input = """
            implementation(project(":core")) {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":core", result.project?.path)
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
    }

    @Test
    fun `should parse project dependency with exclusions`() {
        // Given
        val input = """
            implementation(project(":core")) {
                exclude(group = "org.example", module = "excluded")
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":core", result.project?.path)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("org.example", result.config.exclusions[0].groupId)
        assertEquals("excluded", result.config.exclusions[0].artifactId)
    }

    @Test
    fun `should parse project dependency with multiple configurations`() {
        // Given
        val input = """
            implementation(project(":core", configuration = "default")) {
                capabilities {
                    requireCapability("org.example:feature:1.0")
                }
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                }
                exclude(group = "org.example", module = "excluded")
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("project", result.groupId)
        assertEquals("core", result.artifactId)
        assertEquals("implementation", result.scope)
        assertNotNull(result.project)
        assertEquals(":core", result.project?.path)
        assertEquals("default", result.project?.targetConfiguration)
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.example", result.config.capabilities[0].group)
        assertEquals("feature", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("org.example", result.config.exclusions[0].groupId)
        assertEquals("excluded", result.config.exclusions[0].artifactId)
    }
} 