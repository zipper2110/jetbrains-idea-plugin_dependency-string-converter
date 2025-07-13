package com.litvin.dependency.converter.maven.extractor

import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import com.litvin.dependency.converter.maven.reference.MavenParserTest4Scope
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MavenExtractorTest4Scope {

    private val extractor = MavenDependenciesExtractor()

    @Test
    fun `should extract dependencies from MavenParserTest4Scope`() {
        // Given
        val dependencies = listOf(
            MavenParserTest4Scope.defaultScopeXml,
            MavenParserTest4Scope.compileScopeXml,
            MavenParserTest4Scope.runtimeScopeXml,
            MavenParserTest4Scope.testScopeXml,
            MavenParserTest4Scope.providedScopeXml,
            MavenParserTest4Scope.systemScopeXml
        )
        val input = dependencies.joinToString("\n")

        // When
        val extractedDependencies = extractor.extractDependencies(input)

        // Then
        assertEquals(dependencies.size, extractedDependencies.size)

        // Check the first 5 dependencies
        dependencies.forEachIndexed { index, dependency ->
            assertEquals(dependency.trimIndent(), extractedDependencies[index].trimIndent())
        }
    }
}
