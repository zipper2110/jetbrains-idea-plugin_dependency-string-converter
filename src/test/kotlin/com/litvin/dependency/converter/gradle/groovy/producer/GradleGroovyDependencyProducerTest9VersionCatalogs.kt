package com.litvin.dependency.converter.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy version catalog formats
 * using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest9VersionCatalogs {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce basic catalog reference correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("libs"))
        assertTrue(result.contains("spring.core"))
    }
    
    @Test
    fun `should produce catalog with exclusion correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            config = DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "commons-logging"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("libs"))
        assertTrue(result.contains("spring.core"))
        assertTrue(result.contains("exclude"))
        assertTrue(result.contains("commons-logging"))
    }
    
    @Test
    fun `should produce catalog bundle correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "libs.bundles",
            artifactId = "spring",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("libs.bundles"))
        assertTrue(result.contains("spring"))
    }
    
    @Test
    fun `should produce catalog with version constraints correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            config = DependencyConfig(
                // We'll use capabilities for version constraints since there's no specific field
                capabilities = listOf(
                    CapabilityModel(
                        group = "version",
                        name = "strictly",
                        version = "[1.0,2.0]"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("libs"))
        assertTrue(result.contains("spring.core"))
        // We'll validate the presence of capabilities which is how the producer handles this
        assertTrue(result.contains("capabilities") || result.contains("version"))
    }
    
    @Test
    fun `should produce catalog with capabilities correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            config = DependencyConfig(
                capabilities = listOf(
                    CapabilityModel(
                        group = "org.springframework",
                        name = "spring-core-capability",
                        version = ""
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("libs"))
        assertTrue(result.contains("spring.core"))
        assertTrue(result.contains("capabilities"))
        assertTrue(result.contains("requireCapability"))
        assertTrue(result.contains("spring-core-capability"))
    }
    
    @Test
    fun `should produce catalog with attributes correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            config = DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "java-runtime"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("libs"))
        assertTrue(result.contains("spring.core"))
        assertTrue(result.contains("attributes"))
        assertTrue(result.contains("Usage.USAGE_ATTRIBUTE"))
        assertTrue(result.contains("java-runtime"))
    }
    
    @Test
    fun `should produce complex catalog usage correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            config = DependencyConfig(
                capabilities = listOf(
                    CapabilityModel(
                        group = "org.springframework",
                        name = "spring-core-capability",
                        version = ""
                    )
                ),
                attributes = listOf(
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "java-runtime"
                    )
                ),
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "commons-logging"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("libs"))
        assertTrue(result.contains("spring.core"))
        assertTrue(result.contains("capabilities"))
        assertTrue(result.contains("spring-core-capability"))
        assertTrue(result.contains("attributes"))
        assertTrue(result.contains("Usage.USAGE_ATTRIBUTE"))
        assertTrue(result.contains("exclude"))
        assertTrue(result.contains("commons-logging"))
    }
} 