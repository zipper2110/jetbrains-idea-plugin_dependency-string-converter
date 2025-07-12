package com.litvin.dependency.converter.extractor

import com.litvin.dependency.model.DependencyFormat

class MavenDependenciesExtractor : DependenciesExtractor {

    override val supportedFormat = DependencyFormat.MAVEN

    override fun extractDependencies(input: String): List<String> {
        // Remove comments to avoid false positives
        val cleanContent = removeComments(input)

        // Look for dependency declaration patterns at the start of lines
        val depPattern = Regex("""^\s*<dependency>.*?</dependency>\s*\)""", setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.MULTILINE))

        return depPattern.findAll(cleanContent).map { it.value }.toList()
    }
}