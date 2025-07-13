package com.litvin.dependency.converter.maven.extractor

import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MavenExtractorTestWrapperTags {

    private val extractor = MavenDependenciesExtractor()

    @Test
    fun `should extract dependencies from wrapper tags`() {
        // Given
        val wrappedSingleDependency = MavenExtractorTestWrapperTagsReference.wrappedSingleDependency
        val wrappedMultipleDependencies = MavenExtractorTestWrapperTagsReference.wrappedMultipleDependencies
        val input = wrappedSingleDependency + "\n" + wrappedMultipleDependencies

        // Expected dependencies to be extracted
        val expectedDependencies = listOf(
            MavenExtractorTestWrapperTagsReference.springBootDependency,
            MavenExtractorTestWrapperTagsReference.springCoreDependency,
            MavenExtractorTestWrapperTagsReference.springContextDependency
        )

        // When
        val extractedDependencies = extractor.extractDependencies(input)

        // Then
        assertEquals(expectedDependencies.size, extractedDependencies.size)
        expectedDependencies.forEachIndexed { index, dependency ->
            assertEquals(dependency.trimIndent(),extractedDependencies[index].trimIndent(),
                "Dependency at index $index doesn't match"
            )
        }
    }
}
