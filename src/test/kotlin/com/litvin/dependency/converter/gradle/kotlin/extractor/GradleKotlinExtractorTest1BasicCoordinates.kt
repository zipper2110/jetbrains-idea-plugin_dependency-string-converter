package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest1BasicCoordinates
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest1BasicCoordinates {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract dependencies with basic coordinates`() {
        // Given
        val dependencies = listOf(
            GradleKotlinTest1BasicCoordinates.basicStringNotation,
//            GradleKotlinTest1BasicCoordinates.groupModuleVersion,
            GradleKotlinTest1BasicCoordinates.apiDependency,
            GradleKotlinTest1BasicCoordinates.compileOnlyDependency,
            GradleKotlinTest1BasicCoordinates.noVersionDependency,
            GradleKotlinTest1BasicCoordinates.companyModuleDependency,
            GradleKotlinTest1BasicCoordinates.springBootStarterDependency,
            GradleKotlinTest1BasicCoordinates.quarkusDependency
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
