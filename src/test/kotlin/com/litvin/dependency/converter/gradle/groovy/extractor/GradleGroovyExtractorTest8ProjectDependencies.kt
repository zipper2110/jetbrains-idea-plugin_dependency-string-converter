package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest8ProjectDependencies {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with project dependencies`() {
        // Given
        val dependencies = listOf<String>(
//            GradleGroovyTest8ProjectDependencies.basicProject,
//            GradleGroovyTest8ProjectDependencies.projectWithConfiguration,
//            GradleGroovyTest8ProjectDependencies.projectWithCapabilities,
//            GradleGroovyTest8ProjectDependencies.projectWithAttributes,
//            GradleGroovyTest8ProjectDependencies.projectWithExclusions,
//            GradleGroovyTest8ProjectDependencies.projectWithoutTransitive,
//            GradleGroovyTest8ProjectDependencies.projectWithBuildType,
//            GradleGroovyTest8ProjectDependencies.projectWithConfigAndCapabilities,
//            GradleGroovyTest8ProjectDependencies.complexProject,
//            GradleGroovyTest8ProjectDependencies.multipleProjects
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