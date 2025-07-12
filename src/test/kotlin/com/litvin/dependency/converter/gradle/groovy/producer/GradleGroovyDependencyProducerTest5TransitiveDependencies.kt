package com.litvin.dependency.converter.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest5TransitiveDependencies
import com.litvin.dependency.model.DependencyConfig
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.ExclusionModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of various Gradle Groovy transitive dependency configurations
 * using GradleGroovyDependencyProducer
 */
class GradleGroovyDependencyProducerTest5TransitiveDependencies {
    private val producer = GradleGroovyDependencyProducer()
    
    @Test
    fun `should produce dependency with transitive=false correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = DependencyConfig(
                transitive = false
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest5TransitiveDependencies.transitiveDisabled.trim(), result.trim())
    }
    
    @Test
    fun `should produce dependency with single exclusion correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
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
        assertEquals(GradleGroovyTest5TransitiveDependencies.singleExclusion.trim(), result.trim())
    }
    
    @Test
    fun `should produce dependency with multiple exclusions correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "commons-logging"
                    ),
                    ExclusionModel(
                        groupId = "log4j",
                        artifactId = "log4j"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest5TransitiveDependencies.multipleExclusions.trim(), result.trim())
    }
    
    @Test
    fun `should produce dependency with force=true correctly`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = DependencyConfig(
                force = true
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(GradleGroovyTest5TransitiveDependencies.forceVersion.trim(), result.trim())
    }
} 