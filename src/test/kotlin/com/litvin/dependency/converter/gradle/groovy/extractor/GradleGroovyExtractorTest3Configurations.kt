package com.litvin.dependency.converter.gradle.groovy.extractor

import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import com.litvin.dependency.converter.gradle.groovy.reference.GradleGroovyTest3Configurations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleGroovyExtractorTest3Configurations {

    private val extractor = GradleGroovyDependenciesExtractor()

    @Test
    fun `should extract dependencies with different configurations`() {
        // Given
        val dependencies = listOf(
            GradleGroovyTest3Configurations.implementation,
            GradleGroovyTest3Configurations.api,
            GradleGroovyTest3Configurations.compileOnly,
            GradleGroovyTest3Configurations.runtimeOnly,
            GradleGroovyTest3Configurations.testImplementation,
            GradleGroovyTest3Configurations.testCompileOnly,
            GradleGroovyTest3Configurations.testRuntimeOnly,
            GradleGroovyTest3Configurations.annotationProcessor
        )
        val input = dependencies.joinToString("\n")

        // When
        val extractedDependencies = extractor.extractDependencies(input)

        // Then
        assertEquals(dependencies.size, extractedDependencies.size)

        // Check each dependency
        dependencies.forEachIndexed { index, dependency ->
            assertEquals(dependency.trimIndent(), extractedDependencies[index].trimIndent())
        }
    }
}