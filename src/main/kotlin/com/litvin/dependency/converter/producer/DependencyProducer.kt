package com.litvin.dependency.converter.producer

import com.litvin.dependency.converter.DependencyFormat
import com.litvin.dependency.model.DependencyModel

/**
 * Interface for producers that convert from the internal model to a specific dependency format
 */
interface DependencyProducer {
    /**
     * The format this producer outputs
     */
    val targetFormat: DependencyFormat
    
    /**
     * Converts the internal model to a formatted dependency string
     *
     * @param model The internal dependency model
     * @return The formatted dependency string
     */
    fun produce(model: DependencyModel): String
} 