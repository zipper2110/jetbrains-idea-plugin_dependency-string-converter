package com.litvin.dependency.converter.maven.extractor

import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import com.litvin.dependency.converter.maven.reference.MavenParserTest2VersionSpecification
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MavenExtractorTest2VersionSpecification {

    private val extractor = MavenDependenciesExtractor()

    @Test
    fun `should extract dependencies from MavenParserTest2VersionSpecification`() {
        // Given
        val dependencies = listOf(
            MavenParserTest2VersionSpecification.exactVersionXml,
            MavenParserTest2VersionSpecification.snapshotVersionXml,
            MavenParserTest2VersionSpecification.qualifierVersionXml,
            MavenParserTest2VersionSpecification.timestampVersionXml,
            MavenParserTest2VersionSpecification.versionRangeInclusiveExclusiveXml,
            MavenParserTest2VersionSpecification.releaseVersionXml
        )
        val input = dependencies.joinToString("\n")

        // When
        val extractedDependencies = extractor.extractDependencies(input)

        // Then
        assertEquals(dependencies.size, extractedDependencies.size)
        dependencies.forEachIndexed { index, dependency ->
            assertEquals(dependency, extractedDependencies[index])
        }
    }
}