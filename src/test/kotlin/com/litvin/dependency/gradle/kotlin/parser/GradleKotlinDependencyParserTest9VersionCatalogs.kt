package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest9VersionCatalogs {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse dependency with version catalog reference`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:${'$'}libs.versions.spring.get()")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${libs.versions.spring.get()}", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with version catalog reference in different notation`() {
        // Given
        val input = """
            implementation(group = "org.springframework", name = "spring-core", version = ${'$'}libs.versions.spring.get())
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${libs.versions.spring.get()}", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with version catalog reference and classifier`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:${'$'}libs.versions.spring.get():javadoc")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${libs.versions.spring.get()}", result.version)
        assertEquals("javadoc", result.classifier)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with version catalog reference and extension`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:${'$'}libs.versions.spring.get()@zip")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${libs.versions.spring.get()}", result.version)
        assertEquals("zip", result.type)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with version catalog reference and configurations`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:${'$'}libs.versions.spring.get()") {
                isTransitive = false
                exclude(group = "commons-logging", module = "commons-logging")
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${libs.versions.spring.get()}", result.version)
        assertEquals("implementation", result.scope)
        assertFalse(result.config.transitive)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
    }

    @Test
    fun `should parse dependency with version catalog reference and capabilities`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:${'$'}libs.versions.spring.get()") {
                capabilities {
                    requireCapability("org.springframework:spring-core-capability:1.0")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${libs.versions.spring.get()}", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.springframework", result.config.capabilities[0].group)
        assertEquals("spring-core-capability", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
    }

    @Test
    fun `should parse dependency with version catalog reference and attributes`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:${'$'}libs.versions.spring.get()") {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${libs.versions.spring.get()}", result.version)
        assertEquals("implementation", result.scope)
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
    }

    @Test
    fun `should parse dependency with version catalog reference and multiple configurations`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:${'$'}libs.versions.spring.get()") {
                isTransitive = false
                capabilities {
                    requireCapability("org.springframework:spring-core-capability:1.0")
                }
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                }
                exclude(group = "commons-logging", module = "commons-logging")
                targetConfiguration = "default"
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("\${libs.versions.spring.get()}", result.version)
        assertEquals("implementation", result.scope)
        assertFalse(result.config.transitive)
        assertEquals("default", result.config.targetConfiguration)
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.springframework", result.config.capabilities[0].group)
        assertEquals("spring-core-capability", result.config.capabilities[0].name)
        assertEquals("1.0", result.config.capabilities[0].version)
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("commons-logging", result.config.exclusions[0].artifactId)
    }
} 