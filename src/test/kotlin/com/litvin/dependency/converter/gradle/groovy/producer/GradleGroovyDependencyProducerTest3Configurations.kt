package com.litvin.dependency.converter.gradle.groovy.producer

import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest3Configurations
import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy configuration formats using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest3Configurations {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce implementation configuration correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest3Configurations.implementation.trim(), result.trim())
    }
    
    @Test
    fun `should produce api configuration correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "api"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest3Configurations.api.trim(), result.trim())
    }
    
    @Test
    fun `should produce compileOnly configuration correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "compileOnly"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest3Configurations.compileOnly.trim(), result.trim())
    }
    
    @Test
    fun `should produce runtimeOnly configuration correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "runtimeOnly"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest3Configurations.runtimeOnly.trim(), result.trim())
    }
    
    @Test
    fun `should produce testImplementation configuration correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.junit.jupiter",
            artifactId = "junit-jupiter",
            version = "5.8.2",
            scope = "testImplementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest3Configurations.testImplementation.trim(), result.trim())
    }
    
    @Test
    fun `should produce custom configuration correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "custom-lib",
            version = "1.0.0",
            scope = "myCustomConfiguration"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest3Configurations.customConfiguration.trim(), result.trim())
    }
} 