package com.litvin.dependency.converter.extractor

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyFormat.Companion.COMMON_GRADLE_CONFIGS

interface DependenciesExtractor {

    /**
     * The format this extractor can handle
     */
    val supportedFormat: DependencyFormat

    fun extractDependencies(input: String): List<String>

    /**
     * Removes comments from content
     */
    fun removeComments(content: String): String {
        var result = content

        // Remove single-line comments
        result = result.replace(Regex("""//.*$""", RegexOption.MULTILINE), "")

        // Remove multi-line comments
        result = result.replace(Regex("""/\*.*?\*/""", RegexOption.DOT_MATCHES_ALL), "")

        return result
    }

    /**
     * Checks if the given configuration name is a valid dependency configuration
     */
    fun isValidGradleDependency(config: String): Boolean {
        return COMMON_GRADLE_CONFIGS.contains(config)
    }
}