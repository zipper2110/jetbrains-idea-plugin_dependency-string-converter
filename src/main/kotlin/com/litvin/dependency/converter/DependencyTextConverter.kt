package com.litvin.dependency.converter

import com.litvin.dependency.model.DependencyFormat

class DependencyTextConverter {
    private val registry = DependencyConverterRegistry()

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

        try {
            // Step 1: Parse the input to the internal model
            val parser = registry.getParser(sourceFormat)
            val model = parser.parse(text)
            
            // Step 2: Produce the output format from the model
            val producer = registry.getProducer(targetFormat)
            return producer.produce(model)
        } catch (e: Exception) {
            throw UnsupportedOperationException("Conversion from $sourceFormat to $targetFormat failed: ${e.message}", e)
        }
    }
}