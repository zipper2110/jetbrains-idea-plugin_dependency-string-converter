package com.litvin.dependency.converter

import com.intellij.openapi.diagnostic.Logger
import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.converter.parser.*
import com.litvin.dependency.converter.producer.*
import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor

/**
 * Registry that manages the available parsers and producers for dependency conversion
 */
class DependencyConverterRegistry {

    private val detectors = listOf(
        MavenDetector(),
        GradleKotlinDetector(),
        GradleGroovyDetector(),
    )

    private val extractors = listOf(
        MavenDependenciesExtractor(),
        GradleKotlinDependenciesExtractor(),
        GradleGroovyDependenciesExtractor(),
    )

    private val parsers = listOf(
        MavenDependencyParser(),
        GradleKotlinDependencyParser(),
        GradleGroovyDependencyParser()
    )
    
    private val producers = listOf(
        MavenDependencyProducer(),
        GradleKotlinDependencyProducer(),
        GradleGroovyDependencyProducer()
    )
    
    /**
     * Gets the appropriate parser for the given format
     *
     * @param format The dependency format to parse
     * @return The parser that can handle the given format
     * @throws IllegalArgumentException if no parser is available for the given format
     */
    fun getParser(format: DependencyFormat) = parsers.find { it.supportedFormat == format }
        ?: throw IllegalArgumentException("No parser available for format: $format")
    
    /**
     * Gets the appropriate producer for the given format
     *
     * @param format The dependency format to produce
     * @return The producer that can handle the given format
     * @throws IllegalArgumentException if no producer is available for the given format
     */
    fun getProducer(format: DependencyFormat) = producers.find { it.targetFormat == format }
        ?: throw IllegalArgumentException("No producer available for format: $format")

    /**
     * Gets the appropriate extractor for the given format
     *
     * @param format The dependency format to extract dependencies from
     * @return The extractor that can handle the given format
     * @throws IllegalArgumentException if no extractor is available for the given format
     */
    fun getExtractor(format: DependencyFormat) = extractors.find { it.supportedFormat == format }
        ?: throw IllegalArgumentException("No extractor available for format: $format")


    fun detectPastedTextFormatByContent(input: String): DependencyFormat? {
        val availableDetectors = detectors.filter { it.isInputSupported(input) }
        if (availableDetectors.size > 1) {
            throw IllegalStateException("Expected a single detector to match input, but matched " +
                    "${availableDetectors.size}: " +
                    availableDetectors.joinToString(", ") { it.supportedFormat.displayName })
        }
        return availableDetectors.singleOrNull()?.supportedFormat
    }
}
