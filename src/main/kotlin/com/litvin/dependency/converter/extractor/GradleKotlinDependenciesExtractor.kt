package com.litvin.dependency.converter.extractor

import com.intellij.openapi.diagnostic.Logger
import com.litvin.dependency.model.DependencyFormat

class GradleKotlinDependenciesExtractor : DependenciesExtractor {

    private val logger = Logger.getInstance(GradleKotlinDependenciesExtractor::class.java)

    override val supportedFormat = DependencyFormat.GRADLE_KOTLIN

    override fun extractDependencies(input: String): List<String> {
        // Remove comments to avoid false positives
        logger.debug("Extracting dependencies from:")
        logger.debug("/========================")
        logger.debug("\n" + input)
        logger.debug("=========================/")
        val cleanContent = removeComments(input)

        logger.debug("Removed comments, got this:")
        logger.debug("/========================")
        logger.debug("\n" + cleanContent)
        logger.debug("=========================/")

        // Look for dependency declaration patterns at the start of lines
        val depPattern = Regex("""\s*?(\w+?\s*?\(\s*?["'].*?["']\s*?\))""", RegexOption.MULTILINE)

        return depPattern.findAll(cleanContent).map {
            it.groupValues[1].trim().also { logger.debug("Found dependency: $it") }
        }
            .toList().also {
                logger.debug("Extracted result dependencies (${it.size}):")
                logger.debug("/========================")
                logger.debug("\n" + it.joinToString("\n"))
                logger.debug("=========================/")
            }
    }
}