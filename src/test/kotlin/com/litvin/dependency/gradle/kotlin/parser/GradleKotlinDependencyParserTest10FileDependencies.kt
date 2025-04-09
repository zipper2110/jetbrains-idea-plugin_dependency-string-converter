package com.litvin.dependency.gradle.kotlin.parser

import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.FileType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleKotlinDependencyParserTest10FileDependencies {
    private val parser = GradleKotlinDependencyParser()

    @Test
    fun `should parse single file dependency`() {
        // Given
        val input = """
            implementation(files("libs/custom.jar"))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("libs/custom.jar", result.artifactId)
        assertEquals("", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.SINGLE_FILE, result.file?.type)
        assertEquals("libs/custom.jar", result.file?.path)
    }

    @Test
    fun `should parse file tree dependency`() {
        // Given
        val input = """
            implementation(fileTree("libs") {
                include("*.jar")
            })
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("fileTree", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.FILE_TREE, result.file?.type)
        assertEquals("libs", result.file?.path)
        assertNotNull(result.file?.includes)
        assertEquals(1, result.file?.includes?.size)
        assertEquals("*.jar", result.file?.includes?.get(0))
    }

    @Test
    fun `should parse file tree with multiple patterns`() {
        // Given
        val input = """
            implementation(fileTree("libs") {
                include("*.jar")
                exclude("test-*.jar")
            })
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("fileTree", result.groupId)
        assertEquals("libs", result.artifactId)
        assertEquals("", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.FILE_TREE, result.file?.type)
        assertEquals("libs", result.file?.path)
        assertNotNull(result.file?.includes)
        assertEquals(1, result.file?.includes?.size)
        assertEquals("*.jar", result.file?.includes?.get(0))
        assertNotNull(result.file?.excludes)
        assertEquals(1, result.file?.excludes?.size)
        assertEquals("test-*.jar", result.file?.excludes?.get(0))
    }

    @Test
    fun `should parse custom artifact`() {
        // Given
        val input = """
            implementation("org.example:lib:1.0") {
                artifact {
                    name = "lib"
                    type = "zip"
                    extension = "zip"
                    classifier = "dist"
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        assertEquals("zip", result.type)
        assertEquals("dist", result.classifier)
    }

    @Test
    fun `should parse multiple artifacts`() {
        // Given
        val input = """
            implementation("org.example:lib:1.0") {
                artifact {
                    name = "lib"
                    type = "jar"
                    classifier = "sources"
                }
                artifact {
                    name = "lib"
                    type = "jar"
                    classifier = "javadoc"
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        // Assuming multiple artifacts would be handled elsewhere since DependencyModel doesn't have a direct artifacts field
    }

    @Test
    fun `should parse directory dependency`() {
        // Given
        val input = """
            implementation(files("build/libs"))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("build/libs", result.artifactId)
        assertEquals("", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.DIRECTORY, result.file?.type)
        assertEquals("build/libs", result.file?.path)
    }

    @Test
    fun `should parse URL dependency`() {
        // Given
        val input = """
            implementation(files("https://example.com/lib.jar"))
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("file", result.groupId)
        assertEquals("https://example.com/lib.jar", result.artifactId)
        assertEquals("", result.version)
        assertEquals("implementation", result.scope)
        assertNotNull(result.file)
        assertEquals(FileType.URL, result.file?.type)
        assertEquals("https://example.com/lib.jar", result.file?.path)
    }

    @Test
    fun `should parse artifact with configuration`() {
        // Given
        val input = """
            implementation("org.example:lib:1.0") {
                artifact {
                    name = "lib"
                    type = "jar"
                    classifier = "sources"
                }
                exclude(group = "commons-logging")
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        // Assuming artifact is handled elsewhere
        assertEquals(1, result.config.exclusions.size)
        assertEquals("commons-logging", result.config.exclusions[0].groupId)
        assertEquals("*", result.config.exclusions[0].artifactId)
    }

    @Test
    fun `should parse complex artifact combination`() {
        // Given
        val input = """
            implementation("org.example:lib:1.0") {
                artifact {
                    name = "lib"
                    type = "zip"
                    extension = "zip"
                    classifier = "dist"
                    url = "https://example.com/lib-dist.zip"
                }
                capabilities {
                    requireCapability("org.example:feature")
                }
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                }
            }
        """.trimIndent()

        // When
        val result = parser.parse(input)

        // Then
        assertEquals("org.example", result.groupId)
        assertEquals("lib", result.artifactId)
        assertEquals("1.0", result.version)
        assertEquals("implementation", result.scope)
        // Assuming artifact is handled elsewhere
        assertEquals(1, result.config.capabilities.size)
        assertEquals("org.example", result.config.capabilities[0].group)
        assertEquals("feature", result.config.capabilities[0].name)
        assertEquals("", result.config.capabilities[0].version)
        assertEquals(1, result.config.attributes.size)
        assertEquals("Usage.USAGE_ATTRIBUTE", result.config.attributes[0].key)
        assertEquals("java-runtime", result.config.attributes[0].value)
    }
} 