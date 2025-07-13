package com.litvin.dependency.converter.maven.extractor

import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import com.litvin.dependency.converter.maven.reference.MavenParserTest3TypePackaging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MavenExtractorTest3TypePackaging {

    private val extractor = MavenDependenciesExtractor()

    @Test
    fun `should extract dependencies from MavenParserTest3TypePackaging`() {
        // Given
        val dependencies = listOf(
            MavenParserTest3TypePackaging.defaultTypeXml,
            MavenParserTest3TypePackaging.jarTypeXml,
            MavenParserTest3TypePackaging.warTypeXml,
            MavenParserTest3TypePackaging.earTypeXml,
            MavenParserTest3TypePackaging.pomTypeXml,
            MavenParserTest3TypePackaging.bundleTypeXml
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