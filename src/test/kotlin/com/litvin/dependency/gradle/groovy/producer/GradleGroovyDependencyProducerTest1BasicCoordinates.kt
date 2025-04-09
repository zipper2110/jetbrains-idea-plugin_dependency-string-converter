package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest1BasicCoordinates
import com.litvin.dependency.model.DependencyModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy coordinate formats using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest1BasicCoordinates {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce basic string notation correctly`() {
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
        assertEquals(GradleGroovyTest1BasicCoordinates.basicStringNotation.trim(), result.trim())
    }
    
    @Test
    fun `should produce API dependency correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "com.google.guava",
            artifactId = "guava",
            version = "31.1-jre",
            scope = "api"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest1BasicCoordinates.apiDependency.trim(), result.trim())
    }
    
    @Test
    fun `should produce compileOnly dependency correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.projectlombok",
            artifactId = "lombok",
            version = "1.18.22",
            scope = "compileOnly"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest1BasicCoordinates.compileOnlyDependency.trim(), result.trim())
    }
    
    @Test
    fun `should produce dependency without version correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = null,
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest1BasicCoordinates.noVersionDependency.trim(), result.trim())
    }
    
    @Test
    fun `should produce company module dependency correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "com.mycompany.project",
            artifactId = "module-name",
            version = "1.0.0",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest1BasicCoordinates.companyModuleDependency.trim(), result.trim())
    }
} 