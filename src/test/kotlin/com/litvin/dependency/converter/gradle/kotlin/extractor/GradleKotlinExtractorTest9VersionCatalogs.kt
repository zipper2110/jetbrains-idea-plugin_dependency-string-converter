package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest9VersionCatalogs {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract dependencies from version catalogs`() {
        // Given
        val dependencies = listOf<String>(
//            GradleKotlinTest9VersionCatalogs.basicCatalogReference,
//            GradleKotlinTest9VersionCatalogs.catalogWithConfiguration,
//            GradleKotlinTest9VersionCatalogs.catalogBundle,
//            GradleKotlinTest9VersionCatalogs.catalogWithVersion,
//            GradleKotlinTest9VersionCatalogs.catalogWithCapabilities,
//            GradleKotlinTest9VersionCatalogs.catalogWithAttributes,
//            GradleKotlinTest9VersionCatalogs.multipleCatalogReferences,
//            GradleKotlinTest9VersionCatalogs.catalogWithRichVersion,
//            GradleKotlinTest9VersionCatalogs.complexCatalogUsage
            // Not including catalogDeclaration as it's not a dependency declaration
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
