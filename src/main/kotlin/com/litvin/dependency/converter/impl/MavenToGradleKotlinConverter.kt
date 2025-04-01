package com.litvin.dependency.converter.impl

import com.litvin.dependency.converter.DependencyFormat

class MavenToGradleKotlinConverter : MavenToGradleBaseConverter() {
    override val targetFormat = DependencyFormat.GRADLE_KOTLIN

    override fun supports(sourceFormat: DependencyFormat, targetFormat: DependencyFormat): Boolean {
        return sourceFormat == DependencyFormat.MAVEN && targetFormat == DependencyFormat.GRADLE_KOTLIN
    }

    override fun convert(text: String): String {
        // Extract components from Maven XML
        val groupIdRegex = "<groupId>([^<]+)</groupId>".toRegex()
        val artifactIdRegex = "<artifactId>([^<]+)</artifactId>".toRegex()
        val versionRegex = "<version>([^<]+)</version>".toRegex()

        val groupIdMatch = groupIdRegex.find(text)
        val artifactIdMatch = artifactIdRegex.find(text)
        val versionMatch = versionRegex.find(text)

        if (groupIdMatch == null || artifactIdMatch == null || versionMatch == null) {
            return text
        }

        val groupId = convertMavenPropertyToGradleVariable(groupIdMatch.groupValues[1])
        val artifactId = convertMavenPropertyToGradleVariable(artifactIdMatch.groupValues[1])
        val version = convertMavenPropertyToGradleVariable(versionMatch.groupValues[1])

        // Get classifier and type if present
        val classifierMatch = "<classifier>([^<]+)</classifier>".toRegex().find(text)
        val typeMatch = "<type>([^<]+)</type>".toRegex().find(text)
        val classifier = classifierMatch?.groupValues?.get(1) ?: ""
        val type = typeMatch?.groupValues?.get(1) ?: ""

        // Determine configuration based on scope
        val scopeMatch = "<scope>([^<]+)</scope>".toRegex().find(text)
        val scope = scopeMatch?.groupValues?.get(1) ?: "compile"

        val configuration = when (scope) {
            "test" -> "testImplementation"
            "runtime" -> "runtimeOnly"
            "provided" -> "compileOnly"
            else -> "implementation"
        }

        // Get comment if it exists
        val commentRegex = "<!-- (.+) -->".toRegex()
        val commentMatch = commentRegex.find(text)
        val comment = commentMatch?.groupValues?.get(1) ?: ""

        // Extract exclusions if present
        val exclusions = mutableListOf<String>()
        val exclusionRegex = """<exclusion>\s*<groupId>([^<]+)</groupId>\s*<artifactId>([^<]+)</artifactId>\s*</exclusion>""".toRegex()
        exclusionRegex.findAll(text).forEach { match ->
            val (excludeGroup, excludeModule) = match.destructured
            exclusions.add("exclude(group = \"$excludeGroup\", module = \"$excludeModule\")")
        }

        val quoteMark = "\""
        val commentLine = if (comment.isNotEmpty()) "// $comment\n" else ""

        val classifierPart = if (classifier.isNotEmpty()) ":$classifier" else ""
        val typePart = if (type.isNotEmpty()) "@$type" else ""
        val dependencyLine = "$configuration($quoteMark$groupId:$artifactId:$version$classifierPart$typePart$quoteMark)"
        val exclusionsBlock = if (exclusions.isNotEmpty()) " {\n    ${exclusions.joinToString("\n    ")}\n}" else ""

        return "$commentLine$dependencyLine$exclusionsBlock"
    }
}