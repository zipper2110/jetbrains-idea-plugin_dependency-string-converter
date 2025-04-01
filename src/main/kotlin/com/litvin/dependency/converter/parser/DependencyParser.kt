package com.litvin.dependency.converter.parser

import com.litvin.dependency.converter.DependencyFormat
import com.litvin.dependency.model.DependencyModel

/**
 * Interface for parsers that convert from a specific dependency format to the internal model
 */
interface DependencyParser {
    /**
     * The format this parser can handle
     */
    val supportedFormat: DependencyFormat
    
    /**
     * Parses a dependency string into the internal model
     * 
     * @param text The dependency text to parse
     * @return The parsed dependency model
     */
    fun parse(text: String): DependencyModel
} 