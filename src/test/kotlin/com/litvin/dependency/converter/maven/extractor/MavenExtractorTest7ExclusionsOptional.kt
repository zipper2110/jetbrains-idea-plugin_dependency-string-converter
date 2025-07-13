package com.litvin.dependency.converter.maven.extractor

import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import com.litvin.dependency.converter.maven.reference.MavenParserTest7ExclusionsOptional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MavenExtractorTest7ExclusionsOptional {

    private val extractor = MavenDependenciesExtractor()

    @Test
    fun `should extract dependencies from MavenParserTest7ExclusionsOptional`() {
        // Given
        val dependencies = listOf(
            MavenParserTest7ExclusionsOptional.singleExclusionXml,
            MavenParserTest7ExclusionsOptional.multipleExclusionsXml,
            MavenParserTest7ExclusionsOptional.wildcardExclusionXml,
            MavenParserTest7ExclusionsOptional.optionalDependencyXml,
            MavenParserTest7ExclusionsOptional.exclusionsAndOptionalXml
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