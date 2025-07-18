package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest1BasicCoordinates
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest1BasicCoordinates {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with basic coordinates`() {
        // Given
        val dependencies = listOf(
            GradleGroovyTest1BasicCoordinates.basicStringNotation,
//            GradleGroovyTest1BasicCoordinates.mapNotation,
            GradleGroovyTest1BasicCoordinates.apiDependency,
            GradleGroovyTest1BasicCoordinates.compileOnlyDependency,
            GradleGroovyTest1BasicCoordinates.noVersionDependency,
            GradleGroovyTest1BasicCoordinates.companyModuleDependency,
            GradleGroovyTest1BasicCoordinates.springBootStarterDependency,
            GradleGroovyTest1BasicCoordinates.quarkusDependency
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