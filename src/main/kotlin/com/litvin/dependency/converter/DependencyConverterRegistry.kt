package com.litvin.dependency.converter

import com.intellij.openapi.diagnostic.Logger
import com.litvin.dependency.converter.detector.GradleGroovyDetector
import com.litvin.dependency.converter.detector.GradleKotlinDetector
import com.litvin.dependency.converter.detector.MavenDetector
import com.litvin.dependency.converter.extractor.GradleGroovyDependenciesExtractor
import com.litvin.dependency.converter.extractor.GradleKotlinDependenciesExtractor
import com.litvin.dependency.converter.extractor.MavenDependenciesExtractor
import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.converter.parser.GradleKotlinDependencyParser
import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyFormat

/**
 * Registry that manages the available parsers and producers for dependency conversion
 */
class DependencyConverterRegistry {
    private val logger = Logger.getInstance(javaClass)

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
    fun getParser(format: DependencyFormat): com.litvin.dependency.converter.parser.DependencyParser {
        logger.info("✅ Getting parser for ${format.displayName}")
        return parsers.find { it.supportedFormat == format }
            ?.also { logger.info("✅ Found parser: ${it.javaClass.simpleName}") }
            ?: run {
                logger.error("❌ No parser available for format: ${format.displayName}")
                throw IllegalArgumentException("No parser available for format: $format")
            }
    }

    /**
     * Gets the appropriate producer for the given format
     *
     * @param format The dependency format to produce
     * @return The producer that can handle the given format
     * @throws IllegalArgumentException if no producer is available for the given format
     */
    fun getProducer(format: DependencyFormat): com.litvin.dependency.converter.producer.DependencyProducer {
        logger.info("✅ Getting producer for ${format.displayName}")
        return producers.find { it.targetFormat == format }
            ?.also { logger.info("✅ Found producer: ${it.javaClass.simpleName}") }
            ?: run {
                logger.error("❌ No producer available for format: ${format.displayName}")
                throw IllegalArgumentException("No producer available for format: $format")
            }
    }

    /**
     * Gets the appropriate extractor for the given format
     *
     * @param format The dependency format to extract dependencies from
     * @return The extractor that can handle the given format
     * @throws IllegalArgumentException if no extractor is available for the given format
     */
    fun getExtractor(format: DependencyFormat): com.litvin.dependency.converter.extractor.DependenciesExtractor {
        logger.info("✅ Getting extractor for ${format.displayName}")
        return extractors.find { it.supportedFormat == format }
            ?.also { logger.info("✅ Found extractor: ${it.javaClass.simpleName}") }
            ?: run {
                logger.error("❌ No extractor available for format: ${format.displayName}")
                throw IllegalArgumentException("No extractor available for format: $format")
            }
    }


    fun detectPastedTextFormatByContent(input: String): DependencyFormat? {
        logger.info("✅ Detecting format of pasted text")

        val availableDetectors = detectors.filter { 
            val isSupported = it.isInputSupported(input)
            logger.info("${if (isSupported) "✅" else "❌"} Detector ${it.javaClass.simpleName} ${if (isSupported) "supports" else "does not support"} the input")
            isSupported
        }

        if (availableDetectors.isEmpty()) {
            logger.info("❌ No detectors matched the input")
            return null
        }

        if (availableDetectors.size > 1) {
            val errorMessage = "Expected a single detector to match input, but matched " +
                    "${availableDetectors.size}: " +
                    availableDetectors.joinToString(", ") { it.supportedFormat.displayName }
            logger.error("❌ $errorMessage")
            throw IllegalStateException(errorMessage)
        }

        val format = availableDetectors.single().supportedFormat
        logger.info("✅ Detected format: ${format.displayName}")
        return format
    }
}
