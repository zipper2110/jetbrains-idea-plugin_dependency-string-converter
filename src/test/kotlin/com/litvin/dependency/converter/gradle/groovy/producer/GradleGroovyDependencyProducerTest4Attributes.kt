package com.litvin.dependency.converter.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.AttributeModel
import com.litvin.dependency.model.DependencyConfig
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy attribute formats
 * using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest4Attributes {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce dependency with basic attribute correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE",
                        value = "11"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.springframework"))
        assertTrue(result.contains("spring-core"))
        assertTrue(result.contains("5.3.9"))
        assertTrue(result.contains("attributes"))
        assertTrue(result.contains("TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE"))
        assertTrue(result.contains("11"))
    }
    
    @Test
    fun `should produce dependency with multiple attributes correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE",
                        value = "11"
                    ),
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "'java-runtime'"
                    ),
                    AttributeModel(
                        key = "Category.CATEGORY_ATTRIBUTE",
                        value = "'library'"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.springframework"))
        assertTrue(result.contains("spring-core"))
        assertTrue(result.contains("5.3.9"))
        assertTrue(result.contains("attributes"))
        assertTrue(result.contains("TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE"))
        assertTrue(result.contains("11"))
        assertTrue(result.contains("Usage.USAGE_ATTRIBUTE"))
        assertTrue(result.contains("java-runtime"))
        assertTrue(result.contains("Category.CATEGORY_ATTRIBUTE"))
        assertTrue(result.contains("library"))
    }
    
    @Test
    fun `should produce dependency with custom attribute correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "Attribute.of('my.custom.attribute', String)",
                        value = "custom-value"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.springframework"))
        assertTrue(result.contains("spring-core"))
        assertTrue(result.contains("5.3.9"))
        assertTrue(result.contains("attributes"))
        assertTrue(result.contains("Attribute.of"))
        assertTrue(result.contains("my.custom.attribute"))
        assertTrue(result.contains("String"))
        assertTrue(result.contains("custom-value"))
    }
} 