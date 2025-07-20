package com.litvin.dependency.converter.gradle.kotlin.extractor

import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import com.litvin.dependency.converter.gradle.kotlin.reference.GradleKotlinTest3Configurations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GradleKotlinExtractorTest3Configurations {

    private val extractor = GradleKotlinDependenciesExtractor()

    @Test
    fun `should extract dependencies with different configurations`() {
        // Given
        val dependencies = listOf(
            GradleKotlinTest3Configurations.implementation,
            GradleKotlinTest3Configurations.api,
            GradleKotlinTest3Configurations.compileOnly,
            GradleKotlinTest3Configurations.runtimeOnly,
            GradleKotlinTest3Configurations.testImplementation,
            GradleKotlinTest3Configurations.testCompileOnly,
            GradleKotlinTest3Configurations.testRuntimeOnly,
            GradleKotlinTest3Configurations.annotationProcessor,
            GradleKotlinTest3Configurations.kapt,
            GradleKotlinTest3Configurations.debugImplementation,
            GradleKotlinTest3Configurations.releaseImplementation,
            GradleKotlinTest3Configurations.customConfiguration
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
