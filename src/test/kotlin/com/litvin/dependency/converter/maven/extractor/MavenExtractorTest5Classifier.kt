package com.litvin.dependency.converter.maven.extractor

import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import com.litvin.dependency.converter.maven.reference.MavenParserTest5Classifier
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MavenExtractorTest5Classifier {

    private val extractor = MavenDependenciesExtractor()

    @Test
    fun `should extract dependencies from MavenParserTest5Classifier`() {
        // Given
        val dependencies = listOf(
            MavenParserTest5Classifier.noClassifierXml,
            MavenParserTest5Classifier.javadocClassifierXml,
            MavenParserTest5Classifier.sourcesClassifierXml,
            MavenParserTest5Classifier.testsClassifierXml,
            MavenParserTest5Classifier.nativeWindowsClassifierXml
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