package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.CapabilityModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest7Variants {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce debug variant dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "debugImplementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            debugImplementation('com.example:library:1.0.0')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce release variant dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "releaseImplementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            releaseImplementation('com.example:library:1.0.0')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce flavor variant dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "paidImplementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            paidImplementation('com.example:library:1.0.0')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce combined flavor and build type dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "paidDebugImplementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            paidDebugImplementation('com.example:library:1.0.0')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce variant specific project dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "debugImplementation",
            isProject = true,
            projectPath = ":core"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            debugImplementation(project(':core'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce variant specific file dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "file",
            artifactId = "custom",
            scope = "debugImplementation",
            isFile = true,
            filePath = "libs/custom.jar"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            debugImplementation(files('libs/custom.jar'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce variant specific dependency with configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "debugImplementation",
            transitive = false
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            debugImplementation('com.example:library:1.0.0') {
                transitive = false
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce variant specific dependency with capability`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "debugImplementation",
            capabilities = listOf(
                CapabilityModel(
                    group = "com.example",
                    name = "feature",
                    version = "1.0.0"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            debugImplementation('com.example:library:1.0.0') {
                capabilities {
                    requireCapability('com.example:feature:1.0.0')
                }
            }
        """.trimIndent(), result)
    }
} 