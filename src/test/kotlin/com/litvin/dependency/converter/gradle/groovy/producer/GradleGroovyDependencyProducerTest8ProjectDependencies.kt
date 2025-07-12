package com.litvin.dependency.converter.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.DependencyConfig
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.ExclusionModel
import com.litvin.dependency.model.ProjectConfig
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy project dependency formats using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest8ProjectDependencies {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce basic project dependency correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module"
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("project"))
        assertTrue(result.contains(":other-module"))
    }
    
    @Test
    fun `should produce project dependency with configuration correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module",
                targetConfiguration = "default"
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertTrue(result.contains("implementation"))
        assertTrue(result.contains("project"))
        assertTrue(result.contains("path:"))
        assertTrue(result.contains(":other-module"))
        assertTrue(result.contains("configuration:"))
        assertTrue(result.contains("default"))
    }
    
    @Test
    fun `should produce project dependency with exclusions correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "project",
            artifactId = ":other-module",
            scope = "implementation",
            project = ProjectConfig(
                path = ":other-module"
            ),
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
        assertTrue(result.contains("project"))
        assertTrue(result.contains(":other-module"))
        assertTrue(result.contains("exclude"))
        assertTrue(result.contains("commons-logging"))
    }
    
    @Test
    fun `should produce project dependency with transitive=false correctly`() {
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
} 