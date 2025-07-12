package com.litvin.dependency.converter.extractor

import com.litvin.dependency.model.DependencyFormat

class GradleGroovyDependenciesExtractor : DependenciesExtractor {

    override val supportedFormat = DependencyFormat.GRADLE_GROOVY

    override fun extractDependencies(input: String): List<String> {
        // Remove comments to avoid false positives
        val cleanContent = removeComments(input)

        // Look for dependency declaration patterns at the start of lines
        val depPattern = Regex("""^\s*(\w+)\s*\(\s*["'][^"']*:[^"']*:[^"']*["']\s*\)""", RegexOption.MULTILINE)

        return depPattern.findAll(cleanContent).map { it.groupValues[1] }
            .filter { isValidGradleDependency(it) }
            .toList()
    }
}