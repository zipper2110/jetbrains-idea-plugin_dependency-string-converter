package com.litvin.dependency.gradle.kotlin.producer

import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.AttributeModel
import com.litvin.dependency.gradle.kotlin.reference.GradleKotlinTest4Attributes
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.litvin.dependency.util.KotlinTestUtils.normalizeKotlin

/**
 * Tests that validate producing Gradle Kotlin DSL dependency declarations
 * with different attribute specifications
 */
class GradleKotlinDependencyProducerTest4Attributes {
    private val producer = GradleKotlinDependencyProducer()
    
    @Test
    fun `should produce dependency with basic attribute`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE",
                        value = "11"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.basicAttribute),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with multiple attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE",
                        value = "11"
                    ),
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "java-runtime"
                    ),
                    AttributeModel(
                        key = "Category.CATEGORY_ATTRIBUTE",
                        value = "library"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.multipleAttributes),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with custom attribute`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "Attribute.of(\"my.custom.attribute\", String::class.java)",
                        value = "custom-value"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.customAttribute),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with library elements attribute`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE",
                        value = "jar"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.libraryElements),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with bundling attribute`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "Bundling.BUNDLING_ATTRIBUTE",
                        value = "external"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.bundling),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with documentation type attribute`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "DocsType.DOCS_TYPE_ATTRIBUTE",
                        value = "javadoc"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.documentationType),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with status attribute`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "Status.STATUS_ATTRIBUTE",
                        value = "release"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.status),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with artifact type attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE",
                        value = "jar"
                    ),
                    AttributeModel(
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
            normalizeKotlin(GradleKotlinTest4Attributes.attributesWithArtifactType),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with classifier and attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            classifier = "sources",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "Category.CATEGORY_ATTRIBUTE",
                        value = "documentation"
                    ),
                    AttributeModel(
                        key = "DocsType.DOCS_TYPE_ATTRIBUTE",
                        value = "source"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.attributesWithClassifier),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with complex combination of attributes`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation",
            config = com.litvin.dependency.model.DependencyConfig(
                attributes = listOf(
                    AttributeModel(
                        key = "TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE",
                        value = "11"
                    ),
                    AttributeModel(
                        key = "Usage.USAGE_ATTRIBUTE",
                        value = "java-runtime"
                    ),
                    AttributeModel(
                        key = "Category.CATEGORY_ATTRIBUTE",
                        value = "library"
                    ),
                    AttributeModel(
                        key = "LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE",
                        value = "jar"
                    ),
                    AttributeModel(
                        key = "Bundling.BUNDLING_ATTRIBUTE",
                        value = "external"
                    ),
                    AttributeModel(
                        key = "Status.STATUS_ATTRIBUTE",
                        value = "release"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest4Attributes.complexCombination),
            normalizeKotlin(result)
        )
    }
} 