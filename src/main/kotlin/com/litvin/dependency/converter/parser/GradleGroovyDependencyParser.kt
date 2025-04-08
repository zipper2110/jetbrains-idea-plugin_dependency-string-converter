package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class GradleGroovyDependencyParser : DependencyParser {
    override val supportedFormat = DependencyFormat.GRADLE_GROOVY
    
    // Pattern for dependencies with version
    private val withVersionPattern = Regex(
        "(\\w+)\\s+[\"']([^:]+):([^:]+):([^\"']+)[\"']"
    )
    
    // Pattern for dependencies without version
    private val withoutVersionPattern = Regex(
        "(\\w+)\\s+[\"']([^:]+):([^:]+)[\"']"
    )
    
    override fun parse(text: String): DependencyModel {
        // Try to match with version first
        withVersionPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact, version) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = version.trim('"', '\''),
                scope = configuration.trim()
            )
        }
        
        // If no match, try without version
        withoutVersionPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = null,
                scope = configuration.trim()
            )
        }
        
        throw IllegalArgumentException("Invalid Gradle dependency format")
    }
} 