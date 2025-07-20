package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest4Attributes {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract dependencies with attributes`() {
        // Given
        val dependencies = listOf<String>(
//            GradleKotlinTest4Attributes.basicAttribute,
//            GradleKotlinTest4Attributes.multipleAttributes,
//            GradleKotlinTest4Attributes.customAttribute,
//            GradleKotlinTest4Attributes.libraryElements,
//            GradleKotlinTest4Attributes.bundling,
//            GradleKotlinTest4Attributes.documentationType,
//            GradleKotlinTest4Attributes.status,
//            GradleKotlinTest4Attributes.attributesWithArtifactType,
//            GradleKotlinTest4Attributes.attributesWithClassifier,
//            GradleKotlinTest4Attributes.complexCombination
        )
        val input = dependencies.joinToString("\n\n")

        // Debug logging
        println("[DEBUG_LOG] Input dependencies:")
        dependencies.forEachIndexed { index, dep ->
            println("[DEBUG_LOG] Dependency $index: $dep")
        }

        // When
        val extractedDependencies = extractor.extractDependencies(input)

        // Debug logging
        println("[DEBUG_LOG] Extracted dependencies: ${extractedDependencies.size}")
        extractedDependencies.forEachIndexed { index, dep ->
            println("[DEBUG_LOG] Extracted $index: $dep")
        }

        // Then
        assertEquals(dependencies.size, extractedDependencies.size)

        // Check each dependency
        dependencies.forEachIndexed { index, dependency ->
            assertEquals(dependency.trimIndent(), extractedDependencies[index].trimIndent(),
                "Dependency at index $index doesn't match"
            )
        }
    }
}
