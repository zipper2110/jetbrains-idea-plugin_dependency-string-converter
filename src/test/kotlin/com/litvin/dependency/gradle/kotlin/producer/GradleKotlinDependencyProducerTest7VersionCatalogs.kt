package com.litvin.dependency.gradle.kotlin.producer

import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.gradle.kotlin.reference.GradleKotlinTest9VersionCatalogs
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.litvin.dependency.util.KotlinTestUtils.normalizeKotlin

/**
 * Tests that validate producing Gradle Kotlin DSL version catalog dependency declarations
 */
class GradleKotlinDependencyProducerTest7VersionCatalogs {
    private val producer = GradleKotlinDependencyProducer()
    
    @Test
    fun `should produce basic version catalog reference`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.basicCatalogReference),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce version catalog with configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
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
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.catalogWithConfiguration),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce version catalog bundle`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "bundles.spring",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.catalogBundle),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce version catalog with version`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            version = "[1.0,2.0]"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.catalogWithVersion),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce version catalog with capabilities`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                capabilities = listOf(
                    com.litvin.dependency.model.CapabilityModel(
                        group = "org.springframework",
                        name = "spring-core-capability",
                        version = "1.0"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.catalogWithCapabilities),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce version catalog with attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
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
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.catalogWithAttributes),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce multiple catalog references`() {
        // Given
        val models = listOf(
            DependencyModel(
                groupId = "libs",
                artifactId = "spring.core",
                scope = "implementation"
            ),
            DependencyModel(
                groupId = "libs",
                artifactId = "spring.context",
                scope = "implementation"
            ),
            DependencyModel(
                groupId = "libs",
                artifactId = "junit.jupiter",
                scope = "testImplementation"
            )
        )
        
        // When
        val result = models.joinToString("\n") { producer.produce(it) }
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.multipleCatalogReferences),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce catalog with rich version`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            version = "[1.0,2.0]",
            config = com.litvin.dependency.model.DependencyConfig(
                force = true
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.catalogWithRichVersion),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce complex catalog usage`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "spring.core",
            scope = "implementation",
            version = "[1.0,2.0]",
            config = com.litvin.dependency.model.DependencyConfig(
                capabilities = listOf(
                    com.litvin.dependency.model.CapabilityModel(
                        group = "org.springframework",
                        name = "spring-core-capability",
                        version = "1.0"
                    )
                ),
                attributes = listOf(
                    com.litvin.dependency.model.AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "java-runtime"
                    )
                ),
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
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.complexCatalogUsage),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce version catalog declaration`() {
        // Given
        val model = DependencyModel(
            groupId = "libs",
            artifactId = "spring",
            scope = "version",
            version = "[5.3.0,6.0.0)",
            bundle = com.litvin.dependency.model.BundleConfig(
                dependencies = listOf(
                    com.litvin.dependency.model.BundleModel(
                        groupId = "org.springframework",
                        artifactId = "spring-core",
                        version = "5.3.9"
                    ),
                    com.litvin.dependency.model.BundleModel(
                        groupId = "org.springframework",
                        artifactId = "spring-context",
                        version = "5.3.9"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest9VersionCatalogs.catalogDeclaration),
            normalizeKotlin(result)
        )
    }
} 