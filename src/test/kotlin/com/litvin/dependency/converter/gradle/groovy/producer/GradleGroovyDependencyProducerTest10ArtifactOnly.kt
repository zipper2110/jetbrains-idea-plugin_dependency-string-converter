package com.litvin.dependency.converter.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy artifact-only dependency formats
 * using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest10ArtifactOnly {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce single file dependency correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "file",
            artifactId = "libs/custom.jar",
            scope = "implementation",
            file = FileConfig(
                type = FileType.SINGLE_FILE,
                path = "libs/custom.jar"
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("files"))
        assertTrue(result.contains("libs/custom.jar"))
    }
    
    @Test
    fun `should handle project dependency with transitive property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module"
            ),
            config = DependencyConfig(
                transitive = false
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("project"))
        assertTrue(result.contains(":other-module"))
        assertTrue(result.contains("transitive = false"))
    }
    
    @Test
    fun `should handle artifact with classifier`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "library",
            version = "1.0",
            scope = "implementation",
            classifier = "sources"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("org.example"))
        assertTrue(result.contains("library"))
        assertTrue(result.contains("1.0"))
        assertTrue(result.contains("sources"))
    }
} 