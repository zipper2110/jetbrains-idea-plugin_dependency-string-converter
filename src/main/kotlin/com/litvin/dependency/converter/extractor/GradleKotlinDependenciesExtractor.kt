package com.litvin.dependency.converter.extractor

import com.litvin.dependency.model.DependencyFormat

class GradleKotlinDependenciesExtractor : DependenciesExtractor {

    override val supportedFormat = DependencyFormat.GRADLE_KOTLIN

    override fun extractDependencies(input: String): List<String> {
        // Remove comments to avoid false positives
        val cleanContent = removeComments(input)

        // Look for dependency declaration patterns at the start of lines
        val depPatterns = listOf(
            // Standard notation: implementation "group:artifact:version"
            Regex("""^\s*(\w+)\s+["'][^"']*:[^"']*:[^"']*["']""", RegexOption.MULTILINE),

            // Map notation: implementation group: "...", name: "...", version: "..."
            Regex("""^\s*(\w+)\s+group:\s*["'][^"']+["']""", RegexOption.MULTILINE),
        )

        return depPatterns.flatMap { pattern ->
            pattern.findAll(cleanContent).map { it.groupValues[1] }
                .filter { isValidGradleDependency(it) }
        }
    }
}