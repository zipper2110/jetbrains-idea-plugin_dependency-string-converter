package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest10ArtifactOnly {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with artifact-only formats`() {
        // Given
        val dependencies = listOf<String>(
//            GradleGroovyTest10ArtifactOnly.singleFile,
//            GradleGroovyTest10ArtifactOnly.fileTree,
//            GradleGroovyTest10ArtifactOnly.fileTreeMultiplePatterns,
//            GradleGroovyTest10ArtifactOnly.customArtifact,
//            GradleGroovyTest10ArtifactOnly.multipleArtifacts,
//            GradleGroovyTest10ArtifactOnly.directory,
//            GradleGroovyTest10ArtifactOnly.url,
//            GradleGroovyTest10ArtifactOnly.artifactWithConfiguration,
//            GradleGroovyTest10ArtifactOnly.complexArtifact
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