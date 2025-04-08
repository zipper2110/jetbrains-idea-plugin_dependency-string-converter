package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest2VersionSpecification {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce version range inclusive`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,2.0.0]",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:[1.0.0,2.0.0]'", result)
    }

    @Test
    fun `should produce version range exclusive`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "(1.0.0,2.0.0)",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:(1.0.0,2.0.0)'", result)
    }

    @Test
    fun `should produce version range mixed`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,2.0.0)",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:[1.0.0,2.0.0)'", result)
    }

    @Test
    fun `should produce latest version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "latest.release",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:latest.release'", result)
    }

    @Test
    fun `should produce dynamic version with plus`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.+",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:1.+'", result)
    }

    @Test
    fun `should produce dynamic version with asterisk`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.*",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:1.*'", result)
    }

    @Test
    fun `should produce snapshot version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-SNAPSHOT",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:1.0.0-SNAPSHOT'", result)
    }

    @Test
    fun `should produce release candidate version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-RC1",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:1.0.0-RC1'", result)
    }

    @Test
    fun `should produce milestone version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-M1",
            scope = "implementation"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("implementation 'org.example:my-library:1.0.0-M1'", result)
    }
} 