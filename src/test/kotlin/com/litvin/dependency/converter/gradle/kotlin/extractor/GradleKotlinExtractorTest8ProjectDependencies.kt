package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest8ProjectDependencies {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract project dependencies`() {
        // Given
        val dependencies = listOf<String>(
//            GradleKotlinTest8ProjectDependencies.simpleProject,
//            GradleKotlinTest8ProjectDependencies.nestedProject,
//            GradleKotlinTest8ProjectDependencies.projectWithConfiguration,
//            GradleKotlinTest8ProjectDependencies.projectWithBuildType,
//            GradleKotlinTest8ProjectDependencies.projectWithTransitiveFalse,
//            GradleKotlinTest8ProjectDependencies.projectWithExclude,
//            GradleKotlinTest8ProjectDependencies.projectWithMultipleExcludes,
//            GradleKotlinTest8ProjectDependencies.projectWithCapability,
//            GradleKotlinTest8ProjectDependencies.projectWithAttributes,
//            GradleKotlinTest8ProjectDependencies.projectWithMultipleConfigurations
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
