package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class GradleKotlinDependencyParser : DependencyParser {

    override val supportedFormat = DependencyFormat.GRADLE_KOTLIN

    override fun parse(text: String): DependencyModel {
        val cleanText = text.trim()
        
        // Handle standard notation: implementation("group:artifact:version")
        val standardPattern = Regex("""(\w+)\s*\(\s*["']([^:]+):([^:]+):([^"']+)["']\s*\)""")
        standardPattern.find(cleanText)?.let { match ->
            return DependencyModel(
                groupId = match.groupValues[2],
                artifactId = match.groupValues[3],
                version = match.groupValues[4],
                scope = match.groupValues[1]
            )
        }
        
        // Handle map notation: implementation(group = "...", name = "...", version = "...")
        val mapPattern = Regex("""(\w+)\s*\(\s*group\s*=\s*["']([^"']+)["']\s*,\s*name\s*=\s*["']([^"']+)["']\s*,\s*version\s*=\s*["']([^"']+)["']\s*\)""")
        mapPattern.find(cleanText)?.let { match ->
            return DependencyModel(
                groupId = match.groupValues[2],
                artifactId = match.groupValues[3],
                version = match.groupValues[4],
                scope = match.groupValues[1]
            )
        }
        
        // Handle project dependencies: implementation(project(":module"))
        val projectPattern = Regex("""(\w+)\s*\(\s*project\s*\(\s*["']:([^"']+)["']\s*\)\s*\)""")
        projectPattern.find(cleanText)?.let { match ->
            return DependencyModel(
                groupId = "project",
                artifactId = match.groupValues[2],
                version = "",
                scope = match.groupValues[1]
            )
        }
        
        throw IllegalArgumentException("Unable to parse Gradle Kotlin dependency: $text")
    }

    val dependencyPattern = Regex(
        "(\\w+)\\s*\\(\\s*[\"']([^:]+):([^:]+):([^\"']+)[\"']\\s*\\)"
    )
} 