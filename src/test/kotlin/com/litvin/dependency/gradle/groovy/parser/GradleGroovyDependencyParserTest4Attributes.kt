package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyParserTest4Attributes {
    private val parser = GradleGroovyDependencyParser()

    @Test
    fun `should parse dependency with single attribute`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage, Usage.JAVA_RUNTIME))
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.attributes[0].key)
        assertEquals("Usage.JAVA_RUNTIME", result.attributes[0].value)
    }

    @Test
    fun `should parse dependency with multiple attributes`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage, Usage.JAVA_RUNTIME))
                    attribute(Category.CATEGORY_ATTRIBUTE, project.objects.named(Category, Category.LIBRARY))
                    attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, project.objects.named(LibraryElements, LibraryElements.JAR))
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(3, result.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.attributes[0].key)
        assertEquals("Usage.JAVA_RUNTIME", result.attributes[0].value)
        assertEquals("Category.CATEGORY_ATTRIBUTE", result.attributes[1].key)
        assertEquals("Category.LIBRARY", result.attributes[1].value)
        assertEquals("LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE", result.attributes[2].key)
        assertEquals("LibraryElements.JAR", result.attributes[2].value)
    }

    @Test
    fun `should parse dependency with custom attribute`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                attributes {
                    attribute(Attribute.of("custom.attribute", String), "custom-value")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.attributes.size)
        assertEquals("custom.attribute", result.attributes[0].key)
        assertEquals("custom-value", result.attributes[0].value)
    }

    @Test
    fun `should parse platform dependency`() {
        // Given
        val input = """
            implementation(platform('org.example:platform:1.0.0'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("platform", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertTrue(result.isPlatform)
    }

    @Test
    fun `should parse enforced platform dependency`() {
        // Given
        val input = """
            implementation(enforcedPlatform('org.example:platform:1.0.0'))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("platform", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertTrue(result.isPlatform)
        assertTrue(result.isEnforcedPlatform)
    }

    @Test
    fun `should parse dependency with capability`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                capabilities {
                    requireCapability('org.example:feature:1.0.0')
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.capabilities.size)
        assertEquals("org.example", result.capabilities[0].group)
        assertEquals("feature", result.capabilities[0].name)
        assertEquals("1.0.0", result.capabilities[0].version)
    }

    @Test
    fun `should parse dependency with multiple capabilities`() {
        // Given
        val input = """
            implementation('org.example:my-library:1.0.0') {
                capabilities {
                    requireCapability('org.example:feature1:1.0.0')
                    requireCapability('org.example:feature2:1.0.0')
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("my-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(2, result.capabilities.size)
        assertEquals("org.example", result.capabilities[0].group)
        assertEquals("feature1", result.capabilities[0].name)
        assertEquals("1.0.0", result.capabilities[0].version)
        assertEquals("org.example", result.capabilities[1].group)
        assertEquals("feature2", result.capabilities[1].name)
        assertEquals("1.0.0", result.capabilities[1].version)
    }
} 