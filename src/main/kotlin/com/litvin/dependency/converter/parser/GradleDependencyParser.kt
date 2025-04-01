package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.DependencyModel

/**
 * Base class for Gradle dependency parsers with common functionality
 */
abstract class GradleDependencyParser : DependencyParser {
    protected abstract val dependencyPattern: Regex
    
    override fun parse(text: String): DependencyModel {
        val matchResult = dependencyPattern.find(text)
            ?: throw IllegalArgumentException("Invalid Gradle dependency format")
        
        val (configuration, group, artifact, version) = matchResult.destructured
        
        return DependencyModel(
            groupId = group.trim('"', '\''),
            artifactId = artifact.trim('"', '\''),
            version = version.trim('"', '\''),
            scope = configuration.trim()
        )
    }
} 