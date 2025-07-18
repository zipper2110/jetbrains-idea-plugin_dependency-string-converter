package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest2VersionSpecification
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest2VersionSpecification {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with different version specifications`() {
        // Given
        val dependencies = listOf(
            GradleGroovyTest2VersionSpecification.latestVersion,
            GradleGroovyTest2VersionSpecification.plusNotation,
            GradleGroovyTest2VersionSpecification.latestMinor,
//            GradleGroovyTest2VersionSpecification.versionRangeInclusive,
//            GradleGroovyTest2VersionSpecification.versionRangeExclusive,
//            GradleGroovyTest2VersionSpecification.versionRangeMixed,
//            GradleGroovyTest2VersionSpecification.richVersionPrefer,
//            GradleGroovyTest2VersionSpecification.richVersionReject,
//            GradleGroovyTest2VersionSpecification.richVersionRequire,
//            GradleGroovyTest2VersionSpecification.richVersionStrictly,
//            GradleGroovyTest2VersionSpecification.richVersionCombination,
            GradleGroovyTest2VersionSpecification.snapshotVersion,
            GradleGroovyTest2VersionSpecification.rcVersion,
            GradleGroovyTest2VersionSpecification.betaVersion,
            GradleGroovyTest2VersionSpecification.alphaVersion,
            GradleGroovyTest2VersionSpecification.milestoneVersion
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