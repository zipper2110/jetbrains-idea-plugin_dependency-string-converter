package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest4Attributes {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with attributes`() {
        // Given
        val dependencies = listOf<String>(
//            GradleGroovyTest4Attributes.basicAttribute,
//            GradleGroovyTest4Attributes.multipleAttributes,
//            GradleGroovyTest4Attributes.customAttribute,
//            GradleGroovyTest4Attributes.libraryElements,
//            GradleGroovyTest4Attributes.bundling,
//            GradleGroovyTest4Attributes.documentationType,
//            GradleGroovyTest4Attributes.status,
//            GradleGroovyTest4Attributes.attributesWithArtifactType,
//            GradleGroovyTest4Attributes.attributesWithClassifier,
//            GradleGroovyTest4Attributes.complexCombination
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
