package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest2VersionSpecification
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest2VersionSpecification {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract dependencies with different version specifications`() {
        // Given
        val dependencies = listOf(
            GradleKotlinTest2VersionSpecification.latestVersion,
            GradleKotlinTest2VersionSpecification.plusNotation,
            GradleKotlinTest2VersionSpecification.latestMinor,
//            GradleKotlinTest2VersionSpecification.versionRangeInclusive,
//            GradleKotlinTest2VersionSpecification.versionRangeExclusive,
//            GradleKotlinTest2VersionSpecification.versionRangeMixed,
//            GradleKotlinTest2VersionSpecification.richVersionPrefer,
//            GradleKotlinTest2VersionSpecification.richVersionReject,
//            GradleKotlinTest2VersionSpecification.richVersionRequire,
//            GradleKotlinTest2VersionSpecification.richVersionStrictly,
//            GradleKotlinTest2VersionSpecification.richVersionCombination,
            GradleKotlinTest2VersionSpecification.snapshotVersion,
            GradleKotlinTest2VersionSpecification.rcVersion,
            GradleKotlinTest2VersionSpecification.betaVersion,
            GradleKotlinTest2VersionSpecification.alphaVersion,
            GradleKotlinTest2VersionSpecification.milestoneVersion
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
