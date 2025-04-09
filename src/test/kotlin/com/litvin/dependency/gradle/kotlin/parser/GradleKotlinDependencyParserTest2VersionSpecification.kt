package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest2VersionSpecification {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse dependency with version range`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:[5.3.0,5.4.0)")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("[5.3.0,5.4.0)", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with version range in different notation`() {
        // Given
        val input = """
            implementation(group = "org.springframework", name = "spring-core", version = "[5.3.0,5.4.0)")
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("[5.3.0,5.4.0)", result.version)
        assertEquals("implementation", result.scope)
    }

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
    fun `should parse dependency with version constraint`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                version {
                    strictly("[5.3.0,5.4.0)")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with multiple version constraints`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                version {
                    strictly("[5.3.0,5.4.0)")
                    prefer("5.3.9")
                    reject("5.3.8")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with version substitution`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                version {
                    substitute("5.3.9", "5.3.10")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }

    @Test
    fun `should parse dependency with version alignment`() {
        // Given
        val input = """
            implementation("org.springframework:spring-core:5.3.9") {
                version {
                    align("org.springframework:spring-boot:2.6.3")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
    }
} 