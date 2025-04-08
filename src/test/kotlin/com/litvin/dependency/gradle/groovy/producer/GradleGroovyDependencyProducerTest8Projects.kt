package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest8Projects {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce project dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module"
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation project(':other-module')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module",
                targetConfiguration = "default"
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation project(path: ':other-module', configuration: 'default')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with capabilities`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module"
            ),
            config = DependencyConfig(
                capabilities = listOf(
                    CapabilityModel(
                        group = "org.example",
                        name = "feature",
                        version = "1.0"
                    )
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':other-module')) {
                capabilities {
                    requireCapability('org.example:feature:1.0')
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module"
            ),
            config = DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "java-runtime"
                    )
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':other-module')) {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with exclusions`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module"
            ),
            config = DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "commons-logging"
                    )
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':other-module')) {
                exclude group: 'commons-logging', module: 'commons-logging'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with transitive = false`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module"
            ),
            config = DependencyConfig(
                transitive = false
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':other-module')) {
                transitive = false
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with multiple configurations`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module",
                targetConfiguration = "default"
            ),
            config = DependencyConfig(
                capabilities = listOf(
                    CapabilityModel(
                        group = "org.example",
                        name = "feature1",
                        version = "1.0"
                    ),
                    CapabilityModel(
                        group = "org.example",
                        name = "feature2",
                        version = "1.0"
                    )
                ),
                attributes = listOf(
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "java-runtime"
                    ),
                    AttributeModel(
                        key = "Category.CATEGORY_ATTRIBUTE",
                        value = "library"
                    )
                ),
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "*"
                    )
                ),
                transitive = false
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
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
        """.trimIndent(), result)
    }
} 