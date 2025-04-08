package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest8Modules {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce module dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "implementation",
            module = ModuleConfig()
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(module('com.example:library:1.0.0'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce module dependency with variant`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "debugImplementation",
            module = ModuleConfig()
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            debugImplementation(module('com.example:library:1.0.0'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce module dependency with configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "implementation",
            module = ModuleConfig(),
            transitive = false
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(module('com.example:library:1.0.0')) {
                transitive = false
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce module dependency with dependencies`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "implementation",
            module = ModuleConfig(
                dependencies = listOf(
                    ModuleDependencyModel(
                        groupId = "com.example",
                        artifactId = "core",
                        version = "1.0.0"
                    )
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(module('com.example:library:1.0.0')) {
                dependency('com.example:core:1.0.0')
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce module dependency with multiple dependencies`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "implementation",
            module = ModuleConfig(
                dependencies = listOf(
                    ModuleDependencyModel(
                        groupId = "com.example",
                        artifactId = "core",
                        version = "1.0.0"
                    ),
                    ModuleDependencyModel(
                        groupId = "com.example",
                        artifactId = "utils",
                        version = "1.0.0"
                    )
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(module('com.example:library:1.0.0')) {
                dependency('com.example:core:1.0.0')
                dependency('com.example:utils:1.0.0')
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce module dependency with mixed configurations`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "implementation",
            module = ModuleConfig(
                dependencies = listOf(
                    ModuleDependencyModel(
                        groupId = "com.example",
                        artifactId = "core",
                        version = "1.0.0",
                        transitive = false
                    ),
                    ModuleDependencyModel(
                        groupId = "com.example",
                        artifactId = "utils",
                        version = "1.0.0"
                    )
                )
            ),
            transitive = false
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(module('com.example:library:1.0.0')) {
                transitive = false
                dependency('com.example:core:1.0.0') {
                    transitive = false
                }
                dependency('com.example:utils:1.0.0')
            }
        """.trimIndent(), result)
    }
} 