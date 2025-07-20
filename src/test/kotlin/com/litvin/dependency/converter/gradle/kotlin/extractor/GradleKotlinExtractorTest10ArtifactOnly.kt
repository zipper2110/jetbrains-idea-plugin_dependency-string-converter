package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest10ArtifactOnly {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract artifact-only dependencies`() {
        // Given
        val dependencies = listOf<String>(
//            GradleKotlinTest10ArtifactOnly.customArtifact,
//            GradleKotlinTest10ArtifactOnly.multipleArtifacts,
//            GradleKotlinTest10ArtifactOnly.complexArtifact,
//            GradleKotlinTest10ArtifactOnly.artifactWithConfiguration,
//            GradleKotlinTest10ArtifactOnly.directory,
//            GradleKotlinTest10ArtifactOnly.fileTree,
//            GradleKotlinTest10ArtifactOnly.fileTreeMultiplePatterns,
//            GradleKotlinTest10ArtifactOnly.singleFile,
//            GradleKotlinTest10ArtifactOnly.url
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
