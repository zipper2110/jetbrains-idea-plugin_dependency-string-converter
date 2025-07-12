package com.litvin.dependency.converter.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.CapabilityModel
import com.litvin.dependency.model.DependencyConfig
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy capability formats
 * using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest7Capabilities {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce dependency with basic capability correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "lib",
            version = "1.0",
            scope = "implementation",
            config = DependencyConfig(
                capabilities = listOf(
                    CapabilityModel(
                        group = "org.example",
                        name = "special-feature",
                        version = ""
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.example"))
        assertTrue(result.contains("lib"))
        assertTrue(result.contains("1.0"))
        assertTrue(result.contains("capabilities"))
        assertTrue(result.contains("requireCapability"))
        assertTrue(result.contains("special-feature"))
    }
    
    @Test
    fun `should produce dependency with multiple capabilities correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "lib",
            version = "1.0",
            scope = "implementation",
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
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.example"))
        assertTrue(result.contains("lib"))
        assertTrue(result.contains("1.0"))
        assertTrue(result.contains("capabilities"))
        assertTrue(result.contains("requireCapability"))
        assertTrue(result.contains("feature1"))
        assertTrue(result.contains("feature2"))
    }
    
    @Test
    fun `should produce dependency with capability with version correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "lib",
            version = "1.0",
            scope = "implementation",
            config = DependencyConfig(
                capabilities = listOf(
                    CapabilityModel(
                        group = "org.example",
                        name = "feature",
                        version = "2.0"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.example"))
        assertTrue(result.contains("lib"))
        assertTrue(result.contains("1.0"))
        assertTrue(result.contains("capabilities"))
        assertTrue(result.contains("requireCapability"))
        assertTrue(result.contains("feature"))
        assertTrue(result.contains("2.0"))
    }
} 