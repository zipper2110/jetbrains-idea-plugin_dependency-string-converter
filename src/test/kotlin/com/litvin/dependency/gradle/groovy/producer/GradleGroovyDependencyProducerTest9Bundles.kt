package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest9Bundles {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce bundle dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "bundle",
            version = "1.0.0",
            scope = "implementation",
            bundle = BundleConfig(
                dependencies = listOf(
                    BundleModel(
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
            implementation(platform('com.example:bundle:1.0.0'))
            implementation('com.example:core:1.0.0')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce bundle dependency with multiple dependencies`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "bundle",
            version = "1.0.0",
            scope = "implementation",
            bundle = BundleConfig(
                dependencies = listOf(
                    BundleModel(
                        groupId = "com.example",
                        artifactId = "core",
                        version = "1.0.0"
                    ),
                    BundleModel(
                        groupId = "com.example",
                        artifactId = "utils",
                        version = "1.0.0"
                    ),
                    BundleModel(
                        groupId = "com.example",
                        artifactId = "api",
                        version = "1.0.0"
                    )
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(platform('com.example:bundle:1.0.0'))
            implementation('com.example:core:1.0.0')
            implementation('com.example:utils:1.0.0')
            implementation('com.example:api:1.0.0')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce bundle dependency with variant`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "bundle",
            version = "1.0.0",
            scope = "debugImplementation",
            bundle = BundleConfig(
                dependencies = listOf(
                    BundleModel(
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
            debugImplementation(platform('com.example:bundle:1.0.0'))
            debugImplementation('com.example:core:1.0.0')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce bundle dependency with configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "bundle",
            version = "1.0.0",
            scope = "implementation",
            bundle = BundleConfig(
                dependencies = listOf(
                    BundleModel(
                        groupId = "com.example",
                        artifactId = "core",
                        version = "1.0.0",
                        transitive = false
                    )
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(platform('com.example:bundle:1.0.0'))
            implementation('com.example:core:1.0.0') {
                transitive = false
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce bundle dependency with mixed configurations`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "bundle",
            version = "1.0.0",
            scope = "implementation",
            bundle = BundleConfig(
                dependencies = listOf(
                    BundleModel(
                        groupId = "com.example",
                        artifactId = "core",
                        version = "1.0.0",
                        transitive = false
                    ),
                    BundleModel(
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
            implementation(platform('com.example:bundle:1.0.0'))
            implementation('com.example:core:1.0.0') {
                transitive = false
            }
            implementation('com.example:utils:1.0.0')
        """.trimIndent(), result)
    }
} 