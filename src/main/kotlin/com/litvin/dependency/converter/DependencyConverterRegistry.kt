package com.litvin.dependency.converter

import com.litvin.dependency.converter.parser.*
import com.litvin.dependency.converter.producer.*

/**
 * Registry that manages the available parsers and producers for dependency conversion
 */
class DependencyConverterRegistry {
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
} 