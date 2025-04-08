package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.AttributeModel
import com.litvin.dependency.model.CapabilityModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest4Attributes {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce dependency with single attribute`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
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
            implementation('org.example:my-library:1.0.0') {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage, Usage.JAVA_RUNTIME))
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with multiple attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            attributes = listOf(
                AttributeModel(
                    key = "Usage.USAGE_ATTRIBUTE",
                    value = "Usage.JAVA_RUNTIME"
                ),
                AttributeModel(
                    key = "Category.CATEGORY_ATTRIBUTE",
                    value = "Category.LIBRARY"
                ),
                AttributeModel(
                    key = "LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE",
                    value = "LibraryElements.JAR"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                attributes {
                    attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage, Usage.JAVA_RUNTIME))
                    attribute(Category.CATEGORY_ATTRIBUTE, project.objects.named(Category, Category.LIBRARY))
                    attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, project.objects.named(LibraryElements, LibraryElements.JAR))
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with custom attribute`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            attributes = listOf(
                AttributeModel(
                    key = "custom.attribute",
                    value = "custom-value"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                attributes {
                    attribute(Attribute.of("custom.attribute", String), "custom-value")
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce platform dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "platform",
            version = "1.0.0",
            scope = "implementation",
            isPlatform = true
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(platform('org.example:platform:1.0.0'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce enforced platform dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "platform",
            version = "1.0.0",
            scope = "implementation",
            isPlatform = true,
            isEnforcedPlatform = true
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation(enforcedPlatform('org.example:platform:1.0.0'))
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with capability`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
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
            implementation('org.example:my-library:1.0.0') {
                capabilities {
                    requireCapability('org.example:feature:1.0.0')
                }
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce dependency with multiple capabilities`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0",
            scope = "implementation",
            capabilities = listOf(
                CapabilityModel(
                    group = "org.example",
                    name = "feature1",
                    version = "1.0.0"
                ),
                CapabilityModel(
                    group = "org.example",
                    name = "feature2",
                    version = "1.0.0"
                )
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation('org.example:my-library:1.0.0') {
                capabilities {
                    requireCapability('org.example:feature1:1.0.0')
                    requireCapability('org.example:feature2:1.0.0')
                }
            }
        """.trimIndent(), result)
    }
} 