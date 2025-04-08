package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.AttributeModel
import com.litvin.dependency.model.CapabilityModel
import com.litvin.dependency.model.ExclusionModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest5Projects {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce simple project dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            isProject = true,
            projectPath = ":core"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':core'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce nested project dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "auth",
            scope = "implementation",
            isProject = true,
            projectPath = ":feature:auth"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':feature:auth'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            isProject = true,
            projectPath = ":core",
            targetConfiguration = "api"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(path: ':core', configuration: 'api'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with build type`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            isProject = true,
            projectPath = ":core",
            targetConfiguration = "debug"
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(path: ':core', configuration: 'debug'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with transitive false`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            isProject = true,
            projectPath = ":core",
            transitive = false
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':core')) {
                transitive = false
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with exclude`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            isProject = true,
            projectPath = ":core",
            exclusions = listOf(
                ExclusionModel(
                    groupId = "org.example",
                    artifactId = "excluded"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':core')) {
                exclude group: 'org.example', module: 'excluded'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with capability`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            isProject = true,
            projectPath = ":core",
            capabilities = listOf(
                CapabilityModel(
                    group = "org.example",
                    name = "feature",
                    version = "1.0.0"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':core')) {
                capabilities {
                    requireCapability('org.example:feature:1.0.0')
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce project dependency with attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            isProject = true,
            projectPath = ":core",
            attributes = listOf(
                AttributeModel(
                    key = "Usage.USAGE_ATTRIBUTE",
                    value = "Usage.JAVA_RUNTIME"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(project(':core')) {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage, Usage.JAVA_RUNTIME))
                }
            }
        """.trimIndent(), result)
    }
} 