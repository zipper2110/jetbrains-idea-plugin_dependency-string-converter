package com.litvin.dependency.converter

import com.litvin.dependency.converter.impl.DependencyConversionStrategy
import com.litvin.dependency.converter.impl.GradleToMavenConverter
import com.litvin.dependency.converter.impl.MavenToGradleGroovyConverter
import com.litvin.dependency.converter.impl.MavenToGradleKotlinConverter

class DependencyTextConverter {
    private val strategies: List<DependencyConversionStrategy> = listOf(
        MavenToGradleKotlinConverter(),
        MavenToGradleGroovyConverter(),
        GradleToMavenConverter(DependencyFormat.GRADLE_KOTLIN),
        GradleToMavenConverter(DependencyFormat.GRADLE_GROOVY)
    )

    /**
     * Converts dependency text from one format to another
     *
     * @param text The dependency text to convert
     * @param sourceFormat The format of the input dependency
     * @param targetFormat The desired output format
     * @return The converted dependency text
     * @throws UnsupportedOperationException if the conversion between specified formats is not supported
     */
    fun convertDependency(text: String, sourceFormat: DependencyFormat, targetFormat: DependencyFormat): String {
        // Skip if formats are the same
        if (sourceFormat == targetFormat) {
            return text
        }

        val strategy = strategies.find { it.supports(sourceFormat, targetFormat) }
            ?: throw UnsupportedOperationException("Conversion from $sourceFormat to $targetFormat is not supported")

        return strategy.convert(text)
    }
}