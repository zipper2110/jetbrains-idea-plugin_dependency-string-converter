package com.litvin.dependency.converter.maven.extractor

import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import com.litvin.dependency.converter.maven.reference.MavenParserTest1Coordinates
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MavenExtractorTest1BasicCoordinates {

    private val extractor = MavenDependenciesExtractor()

    @Test
    fun `should extract dependencies from MavenParserTest1Coordinates`() {
        // Given
        val dependencies = listOf(
            MavenParserTest1Coordinates.springCoreXml,
            MavenParserTest1Coordinates.guavaXml,
            MavenParserTest1Coordinates.commonsLangXml,
            MavenParserTest1Coordinates.companyModuleXml,
            MavenParserTest1Coordinates.springBootStarterXml,
            MavenParserTest1Coordinates.quarkusCoreXml
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