package com.litvin.dependency.converter

import com.intellij.openapi.diagnostic.Logger
import com.litvin.dependency.model.DependencyFormat

class DependencyTextConverter {
    private val logger = Logger.getInstance(javaClass)
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
        logger.info("✅ Converting single dependency from ${sourceFormat.displayName} to ${targetFormat.displayName}")

        if (sourceFormat == targetFormat) {
            logger.info("✅ Source and target formats are the same, no conversion needed")
            return text
        }

        try {
            // Step 1: Parse the input to the internal model
            logger.info("✅ Getting parser for ${sourceFormat.displayName}")
            val parser = registry.getParser(sourceFormat)

            logger.info("✅ Parsing dependency text to internal model")
            val model = parser.parse(text)
            logger.info("✅ Successfully parsed dependency: ${model.groupId}:${model.artifactId}")

            // Step 2: Produce the output format from the model
            logger.info("✅ Getting producer for ${targetFormat.displayName}")
            val producer = registry.getProducer(targetFormat)

            logger.info("✅ Producing output in ${targetFormat.displayName} format")
            val result = producer.produce(model)
            logger.info("✅ Successfully converted dependency")

            return result
        } catch (e: Exception) {
            logger.error("❌ Conversion failed from ${sourceFormat.displayName} to ${targetFormat.displayName}: ${e.message}", e)
            throw UnsupportedOperationException("Conversion from $sourceFormat to $targetFormat failed: ${e.message}", e)
        }
    }

    /**
     * Converts multiple dependencies from one format to another
     *
     * @param text The text containing multiple dependencies to convert
     * @param sourceFormat The format of the input dependencies
     * @param targetFormat The desired output format
     * @return The converted dependencies text
     */
    fun convertDependencies(text: String, sourceFormat: DependencyFormat, targetFormat: DependencyFormat): String {
        logger.info("✅ Converting multiple dependencies from ${sourceFormat.displayName} to ${targetFormat.displayName}")

        if (sourceFormat == targetFormat) {
            logger.info("✅ Source and target formats are the same, no conversion needed")
            return text
        }

        logger.info("✅ Getting extractor for ${sourceFormat.displayName}")
        val extractor = registry.getExtractor(sourceFormat)

        logger.info("✅ Extracting dependencies from input text")
        val dependencies = extractor.extractDependencies(text)
        logger.info("✅ Found ${dependencies.size} dependencies to convert")

        val convertedDependencies = dependencies.mapNotNull { depText ->
            try {
                logger.info("✅ Converting dependency: ${depText.take(50)}${if (depText.length > 50) "..." else ""}")
                convertDependency(depText, sourceFormat, targetFormat)
            } catch (e: Exception) {
                // Log warning and skip invalid dependency
                logger.warn("❌ Skipping invalid dependency: ${depText.take(50)}${if (depText.length > 50) "..." else ""} - ${e.message}")
                null
            }
        }

        logger.info("✅ Successfully converted ${convertedDependencies.size} dependencies")
        return convertedDependencies.joinToString("\n")
    }
}
