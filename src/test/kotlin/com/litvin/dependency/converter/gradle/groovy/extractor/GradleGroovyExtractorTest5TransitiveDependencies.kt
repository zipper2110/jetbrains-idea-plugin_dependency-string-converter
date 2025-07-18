package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest5TransitiveDependencies {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with transitive dependency configurations`() {
        // Given
        val dependencies = listOf<String>(
//            GradleGroovyTest5TransitiveDependencies.singleExclusion,
//            GradleGroovyTest5TransitiveDependencies.multipleExclusions,
//            GradleGroovyTest5TransitiveDependencies.groupOnlyExclusion,
//            GradleGroovyTest5TransitiveDependencies.moduleOnlyExclusion,
//            GradleGroovyTest5TransitiveDependencies.transitiveDisabled,
//            GradleGroovyTest5TransitiveDependencies.capabilityConflict,
//            GradleGroovyTest5TransitiveDependencies.dependencySubstitution,
//            GradleGroovyTest5TransitiveDependencies.forceVersion,
//            GradleGroovyTest5TransitiveDependencies.excludeAllTransitive,
//            GradleGroovyTest5TransitiveDependencies.complexCombination,
//            GradleGroovyTest5TransitiveDependencies.configurationExclusions,
//            GradleGroovyTest5TransitiveDependencies.dependencyConstraints
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