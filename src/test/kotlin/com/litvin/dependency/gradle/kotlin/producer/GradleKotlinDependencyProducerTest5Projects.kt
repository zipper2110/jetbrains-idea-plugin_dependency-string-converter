package com.litvin.dependency.gradle.kotlin.producer

import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.AttributeModel
import com.litvin.dependency.model.CapabilityModel
import com.litvin.dependency.model.ExclusionModel
import com.litvin.dependency.model.ProjectConfig
import com.litvin.dependency.gradle.kotlin.reference.GradleKotlinTest8ProjectDependencies
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.litvin.dependency.util.KotlinTestUtils.normalizeKotlin

/**
 * Tests that validate producing Gradle Kotlin DSL project dependency declarations
 */
class GradleKotlinDependencyProducerTest5Projects {
    private val producer = GradleKotlinDependencyProducer()
    
    @Test
    fun `should produce simple project dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(path = ":core")
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.simpleProject),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce nested project dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "auth",
            scope = "implementation",
            project = ProjectConfig(path = ":feature:auth")
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.nestedProject),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce project dependency with configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(
                path = ":core",
                targetConfiguration = "api"
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.projectWithConfiguration),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce project dependency with build type`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(
                path = ":core",
                targetConfiguration = "debug"
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.projectWithBuildType),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce project dependency with transitive false`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(path = ":core"),
            config = com.litvin.dependency.model.DependencyConfig(
                transitive = false
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.projectWithTransitiveFalse),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce project dependency with exclude`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(path = ":core"),
            config = com.litvin.dependency.model.DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "org.example",
                        artifactId = "excluded"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.projectWithExclude),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce project dependency with multiple excludes`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(path = ":core"),
            config = com.litvin.dependency.model.DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "org.example",
                        artifactId = "excluded1"
                    ),
                    ExclusionModel(
                        groupId = "org.example",
                        artifactId = "excluded2"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.projectWithMultipleExcludes),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce project dependency with capability`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(path = ":core"),
            config = com.litvin.dependency.model.DependencyConfig(
                capabilities = listOf(
                    CapabilityModel(
                        group = "org.example",
                        name = "feature",
                        version = "1.0.0"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.projectWithCapability),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce project dependency with attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(path = ":core"),
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "Usage.JAVA_RUNTIME"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.projectWithAttributes),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce project dependency with multiple configurations`() {
        // Given
        val model = DependencyModel(
            groupId = "project",
            artifactId = "core",
            scope = "implementation",
            project = ProjectConfig(
                path = ":core",
                targetConfiguration = "api"
            ),
            config = com.litvin.dependency.model.DependencyConfig(
                transitive = false,
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "org.example",
                        artifactId = "excluded"
                    )
                ),
                capabilities = listOf(
                    CapabilityModel(
                        group = "org.example",
                        name = "feature",
                        version = "1.0.0"
                    )
                ),
                attributes = listOf(
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "Usage.JAVA_RUNTIME"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest8ProjectDependencies.projectWithMultipleConfigurations),
            normalizeKotlin(result)
        )
    }
} 