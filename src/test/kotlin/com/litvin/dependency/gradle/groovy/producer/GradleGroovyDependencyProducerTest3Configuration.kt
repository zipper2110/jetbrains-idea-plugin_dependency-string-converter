package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.ExclusionModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest3Configuration {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce dependency with transitive false`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            transitive = false
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                transitive = false
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with changing true`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            changing = true
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                changing = true
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with force true`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            force = true
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                force = true
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with exclude`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            exclusions = listOf(
                ExclusionModel(
                    groupId = "org.excluded",
                    artifactId = "excluded-module"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                exclude group: 'org.excluded', module: 'excluded-module'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with multiple excludes`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            exclusions = listOf(
                ExclusionModel(
                    groupId = "org.excluded1",
                    artifactId = "excluded-module1"
                ),
                ExclusionModel(
                    groupId = "org.excluded2",
                    artifactId = "excluded-module2"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                exclude group: 'org.excluded1', module: 'excluded-module1'
                exclude group: 'org.excluded2', module: 'excluded-module2'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with classifier`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            classifier = "sources",
            transitive = false
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0:sources') {
                transitive = false
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with extension`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            type = "jar",
            transitive = false
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0@jar') {
                transitive = false
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with multiple configuration options`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            transitive = false,
            changing = true,
            force = true,
            exclusions = listOf(
                ExclusionModel(
                    groupId = "org.excluded",
                    artifactId = "excluded-module"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                transitive = false
                changing = true
                force = true
                exclude group: 'org.excluded', module: 'excluded-module'
            }
        """.trimIndent(), result)
    }
} 