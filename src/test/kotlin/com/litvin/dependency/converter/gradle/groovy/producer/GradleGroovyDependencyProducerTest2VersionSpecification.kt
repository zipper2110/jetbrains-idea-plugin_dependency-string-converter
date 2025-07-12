package com.litvin.dependency.converter.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy version specification formats
 * using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest2VersionSpecification {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce dynamic latest version correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "latest.release",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.springframework"))
        assertTrue(result.contains("spring-core"))
        assertTrue(result.contains("latest.release"))
    }
    
    @Test
    fun `should produce dynamic plus notation correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.+",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.springframework"))
        assertTrue(result.contains("spring-core"))
        assertTrue(result.contains("5.+"))
    }
    
    @Test
    fun `should produce dynamic latest minor correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.+",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.springframework"))
        assertTrue(result.contains("spring-core"))
        assertTrue(result.contains("5.3.+"))
    }
    
    @Test
    fun `should produce snapshot version correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "1.0.0-SNAPSHOT",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.springframework"))
        assertTrue(result.contains("spring-core"))
        assertTrue(result.contains("1.0.0-SNAPSHOT"))
    }
    
    @Test
    fun `should produce release candidate version correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "1.0.0-rc.1",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.springframework"))
        assertTrue(result.contains("spring-core"))
        assertTrue(result.contains("1.0.0-rc.1"))
    }
} 