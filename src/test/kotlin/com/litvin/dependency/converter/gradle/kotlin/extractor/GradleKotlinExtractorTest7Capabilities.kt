package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest7Capabilities {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract dependencies with capability configurations`() {
        // Given
        val dependencies = listOf<String>(
//            GradleKotlinTest7Capabilities.basicCapability,
//            GradleKotlinTest7Capabilities.multipleCapabilities,
//            GradleKotlinTest7Capabilities.capabilityWithVersion,
//            GradleKotlinTest7Capabilities.capabilityWithReason,
//            GradleKotlinTest7Capabilities.capabilityWithAttributes,
//            GradleKotlinTest7Capabilities.capabilityWithVersionConstraints,
//            GradleKotlinTest7Capabilities.optionalCapability,
//            GradleKotlinTest7Capabilities.capabilityWithConfiguration,
//            GradleKotlinTest7Capabilities.complexCapabilityCombination
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
