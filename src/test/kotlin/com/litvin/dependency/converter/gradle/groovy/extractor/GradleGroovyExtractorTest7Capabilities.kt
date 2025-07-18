package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest7Capabilities {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with capability configurations`() {
        // Given
        val dependencies = listOf<String>(
//            GradleGroovyTest7Capabilities.basicCapability,
//            GradleGroovyTest7Capabilities.multipleCapabilities,
//            GradleGroovyTest7Capabilities.capabilityWithVersion,
//            GradleGroovyTest7Capabilities.capabilityWithReason,
//            GradleGroovyTest7Capabilities.capabilityWithAttributes,
//            GradleGroovyTest7Capabilities.capabilityWithVersionConstraints,
//            GradleGroovyTest7Capabilities.optionalCapability,
//            GradleGroovyTest7Capabilities.capabilityWithConfiguration,
//            GradleGroovyTest7Capabilities.complexCapabilityCombination
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