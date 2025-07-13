package com.litvin.dependency.converter.maven.extractor

import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import com.litvin.dependency.converter.maven.reference.MavenParserTest6PropertiesVariables
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MavenExtractorTest6PropertiesVariables {

    private val extractor = MavenDependenciesExtractor()

    @Test
    fun `should extract dependencies from MavenParserTest6PropertiesVariables`() {
        // Given
        val dependencies = listOf(
            MavenParserTest6PropertiesVariables.versionPropertyXml,
            MavenParserTest6PropertiesVariables.groupIdPropertyXml,
            MavenParserTest6PropertiesVariables.artifactIdPropertyXml,
            MavenParserTest6PropertiesVariables.multiplePropertiesXml
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