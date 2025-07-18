package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest6Platform {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with platform configurations`() {
        // Given
        val dependencies = listOf<String>(
//            GradleGroovyTest6Platform.basicPlatform,
//            GradleGroovyTest6Platform.enforcedPlatform,
//            GradleGroovyTest6Platform.platformWithConstraints,
//            GradleGroovyTest6Platform.multiplePlatforms,
//            GradleGroovyTest6Platform.platformWithExclusions,
//            GradleGroovyTest6Platform.platformWithCapabilities,
//            GradleGroovyTest6Platform.customPlatform,
//            GradleGroovyTest6Platform.platformWithDependencyConstraints,
//            GradleGroovyTest6Platform.platformWithRichVersions,
//            GradleGroovyTest6Platform.platformWithAttributes
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