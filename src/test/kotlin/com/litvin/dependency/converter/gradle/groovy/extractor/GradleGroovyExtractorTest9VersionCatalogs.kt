package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest9VersionCatalogs {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with version catalogs`() {
        // Given
        val dependencies = listOf<String>(
//            GradleGroovyTest9VersionCatalogs.basicCatalogReference,
//            GradleGroovyTest9VersionCatalogs.catalogWithConfiguration,
//            GradleGroovyTest9VersionCatalogs.catalogBundle,
//            GradleGroovyTest9VersionCatalogs.catalogWithVersion,
//            GradleGroovyTest9VersionCatalogs.catalogWithCapabilities,
//            GradleGroovyTest9VersionCatalogs.catalogWithAttributes,
//            GradleGroovyTest9VersionCatalogs.multipleCatalogReferences,
//            GradleGroovyTest9VersionCatalogs.catalogWithRichVersion,
//            GradleGroovyTest9VersionCatalogs.complexCatalogUsage,
//            GradleGroovyTest9VersionCatalogs.catalogDeclaration
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