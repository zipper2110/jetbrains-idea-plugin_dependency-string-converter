package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest6Platform {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract dependencies with platform configurations`() {
        // Given
        val dependencies = listOf<String>(
//            GradleKotlinTest6Platform.basicPlatform,
//            GradleKotlinTest6Platform.platformWithDependency,
//            GradleKotlinTest6Platform.multiplePlatforms,
//            GradleKotlinTest6Platform.platformEnforcedVersion,
//            GradleKotlinTest6Platform.platformWithConstraints,
//            GradleKotlinTest6Platform.platformWithCapabilities,
//            GradleKotlinTest6Platform.platformWithAttributes,
//            GradleKotlinTest6Platform.customPlatform,
//            GradleKotlinTest6Platform.platformWithExclusions,
//            GradleKotlinTest6Platform.complexPlatformCombination
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
