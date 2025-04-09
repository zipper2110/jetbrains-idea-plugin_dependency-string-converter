package com.litvin.dependency.gradle.kotlin.producer

import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.FileConfig
import com.litvin.dependency.gradle.kotlin.reference.GradleKotlinTest10ArtifactOnly
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.litvin.dependency.util.KotlinTestUtils.normalizeKotlin

/**
 * Tests that validate producing Gradle Kotlin DSL file dependency declarations
 */
class GradleKotlinDependencyProducerTest6FileDependencies {
    private val producer = GradleKotlinDependencyProducer()
    
    @Test
    fun `should produce single file dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "file",
            artifactId = "custom",
            scope = "implementation",
            file = FileConfig(path = "libs/custom.jar")
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.singleFile),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce file tree dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "file",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(
                path = "libs",
                include = listOf("*.jar")
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.fileTree),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce file tree with multiple patterns`() {
        // Given
        val model = DependencyModel(
            groupId = "file",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(
                path = "libs",
                include = listOf("*.jar"),
                exclude = listOf("test-*.jar")
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.fileTreeMultiplePatterns),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce custom artifact dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "lib",
            version = "1.0",
            scope = "implementation",
            type = "zip",
            extension = "zip",
            classifier = "dist"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.customArtifact),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce multiple artifacts dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "lib",
            version = "1.0",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                artifacts = listOf(
                    com.litvin.dependency.model.ArtifactModel(
                        name = "lib",
                        type = "jar",
                        classifier = "sources"
                    ),
                    com.litvin.dependency.model.ArtifactModel(
                        name = "lib",
                        type = "jar",
                        classifier = "javadoc"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.multipleArtifacts),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce directory dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "file",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(path = "build/libs")
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.directory),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce URL dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "file",
            artifactId = "lib",
            scope = "implementation",
            file = FileConfig(path = "https://example.com/lib.jar")
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.url),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce artifact with configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "lib",
            version = "1.0",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                artifacts = listOf(
                    com.litvin.dependency.model.ArtifactModel(
                        name = "lib",
                        type = "jar",
                        classifier = "sources"
                    )
                ),
                exclusions = listOf(
                    com.litvin.dependency.model.ExclusionModel(
                        groupId = "commons-logging"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.artifactWithConfiguration),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce complex artifact combination`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "lib",
            version = "1.0",
            scope = "implementation",
            type = "zip",
            extension = "zip",
            classifier = "dist",
            config = com.litvin.dependency.model.DependencyConfig(
                artifacts = listOf(
                    com.litvin.dependency.model.ArtifactModel(
                        name = "lib",
                        type = "zip",
                        extension = "zip",
                        classifier = "dist",
                        url = "https://example.com/lib-dist.zip"
                    )
                ),
                capabilities = listOf(
                    com.litvin.dependency.model.CapabilityModel(
                        group = "org.example",
                        name = "feature"
                    )
                ),
                attributes = listOf(
                    com.litvin.dependency.model.AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "java-runtime"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest10ArtifactOnly.complexArtifact),
            normalizeKotlin(result)
        )
    }
} 