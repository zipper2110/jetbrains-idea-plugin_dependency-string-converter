package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest10Constraints {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce dependency constraint`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "constraints",
            constraint = ConstraintConfig()
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            constraints {
                implementation('com.example:library:1.0.0')
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency constraint with version range`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "[1.0.0,2.0.0)",
            scope = "constraints",
            constraint = ConstraintConfig()
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            constraints {
                implementation('com.example:library') {
                    version {
                        strictly '[1.0.0,2.0.0)'
                    }
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency constraint with reject version`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "constraints",
            constraint = ConstraintConfig(
                rejectVersions = listOf("1.1.0", "1.2.0")
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            constraints {
                implementation('com.example:library:1.0.0') {
                    reject('1.1.0', '1.2.0')
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency constraint with reason`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "constraints",
            constraint = ConstraintConfig(
                reason = "Security vulnerability in older versions"
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            constraints {
                implementation('com.example:library:1.0.0') {
                    because 'Security vulnerability in older versions'
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency constraint with variant`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "debugConstraints",
            constraint = ConstraintConfig()
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            debugConstraints {
                implementation('com.example:library:1.0.0')
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency constraint with multiple configurations`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "library",
            version = "1.0.0",
            scope = "constraints",
            constraint = ConstraintConfig(
                rejectVersions = listOf("1.1.0"),
                reason = "Security fix"
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            constraints {
                implementation('com.example:library:1.0.0') {
                    reject('1.1.0')
                    because 'Security fix'
                }
            }
        """.trimIndent(), result)
    }
} 