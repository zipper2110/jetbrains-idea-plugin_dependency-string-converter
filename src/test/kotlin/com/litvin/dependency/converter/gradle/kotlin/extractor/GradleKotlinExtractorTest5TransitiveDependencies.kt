package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest5TransitiveDependencies {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract dependencies with transitive configurations`() {
        // Given
        val dependencies = listOf<String>(
//            GradleKotlinTest5TransitiveDependencies.singleExclusion,
//            GradleKotlinTest5TransitiveDependencies.multipleExclusions,
//            GradleKotlinTest5TransitiveDependencies.groupOnlyExclusion,
//            GradleKotlinTest5TransitiveDependencies.moduleOnlyExclusion,
//            GradleKotlinTest5TransitiveDependencies.transitiveDisabled,
//            GradleKotlinTest5TransitiveDependencies.capabilityConflict,
//            GradleKotlinTest5TransitiveDependencies.dependencySubstitution,
//            GradleKotlinTest5TransitiveDependencies.forceVersion,
//            GradleKotlinTest5TransitiveDependencies.excludeAllTransitive,
//            GradleKotlinTest5TransitiveDependencies.complexCombination,
//            GradleKotlinTest5TransitiveDependencies.configurationExclusions,
//            GradleKotlinTest5TransitiveDependencies.dependencyConstraints
        )
        val input = dependencies.joinToString("\n")

        // When
        val extractedDependencies = extractor.extractDependencies(input)

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
