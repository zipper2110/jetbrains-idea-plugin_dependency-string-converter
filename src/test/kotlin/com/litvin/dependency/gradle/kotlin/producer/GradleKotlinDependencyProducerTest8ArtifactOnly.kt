package com.litvin.dependency.gradle.kotlin.producer

import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.FileConfig
import com.litvin.dependency.model.FileType
import com.litvin.dependency.gradle.kotlin.reference.GradleKotlinTest10ArtifactOnly
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.litvin.dependency.util.KotlinTestUtils.normalizeKotlin

/**
 * Tests that validate producing Gradle Kotlin DSL artifact-only dependency declarations
 */
class GradleKotlinDependencyProducerTest8ArtifactOnly {
    private val producer = GradleKotlinDependencyProducer()
    
    @Test
    fun `should produce single file dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "file",
            artifactId = "custom",
            scope = "implementation",
            file = FileConfig(
                type = FileType.SINGLE_FILE,
                path = "libs/custom.jar"
            )
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
                type = FileType.FILE_TREE,
                path = "libs",
                includes = listOf("*.jar")
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
                type = FileType.FILE_TREE,
                path = "libs",
                includes = listOf("*.jar"),
                excludes = listOf("test-*.jar")
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
            type = "jar",
            classifier = "sources"
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
            file = FileConfig(
                type = FileType.DIRECTORY,
                path = "build/libs"
            )
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
            file = FileConfig(
                type = FileType.URL,
                path = "https://example.com/lib.jar"
            )
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
            type = "jar",
            classifier = "sources",
            config = com.litvin.dependency.model.DependencyConfig(
                exclusions = listOf(
                    com.litvin.dependency.model.ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "*"
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
            classifier = "dist",
            config = com.litvin.dependency.model.DependencyConfig(
                capabilities = listOf(
                    com.litvin.dependency.model.CapabilityModel(
                        group = "org.example",
                        name = "feature",
                        version = "1.0"
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