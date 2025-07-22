package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.*

class GradleKotlinDependencyParser : DependencyParser {

    override val supportedFormat = DependencyFormat.GRADLE_KOTLIN

    // Pattern for dependencies with version in basic string notation
    private val basicStringPattern = Regex(
        "(\\w+)\\s*\\(\\s*[\"']([^:]+):([^:]+):([^\"']+)[\"']\\s*\\)"
    )

    // Pattern for dependencies without version
    private val withoutVersionPattern = Regex(
        "(\\w+)\\s*\\(\\s*[\"']([^:]+):([^:]+)[\"']\\s*\\)"
    )

    // Pattern for map notation
    private val mapNotationPattern = Regex(
        "(\\w+)\\s*\\(\\s*group\\s*=\\s*[\"']([^\"']+)[\"']\\s*,\\s*name\\s*=\\s*[\"']([^\"']+)[\"']\\s*,\\s*version\\s*=\\s*[\"']([^\"']+)[\"']\\s*\\)"
    )

    // Pattern for platform dependencies
    private val platformPattern = Regex(
        "(\\w+)\\s*\\(\\s*platform\\s*\\(\\s*[\"']([^:]+):([^:]+):([^\"']+)[\"']\\s*\\)\\s*\\)"
    )

    // Pattern for enforced platform dependencies
    private val enforcedPlatformPattern = Regex(
        "(\\w+)\\s*\\(\\s*enforcedPlatform\\s*\\(\\s*[\"']([^:]+):([^:]+):([^\"']+)[\"']\\s*\\)\\s*\\)"
    )

    // Pattern for project dependencies
    private val projectPattern = Regex(
        "(\\w+)\\s*\\(\\s*project\\s*\\(\\s*[\"']([^\"']+)[\"']\\s*\\)\\s*\\)"
    )

    // Pattern for configuration blocks
    private val exclusionPattern = Regex("exclude\\s*\\(\\s*group\\s*=\\s*[\"']([^\"']+)[\"'](?:\\s*,\\s*module\\s*=\\s*[\"']([^\"']+)[\"'])?\\s*\\)")
    private val transitivePattern = Regex("transitive\\s*=\\s*(true|false)")
    private val changingPattern = Regex("changing\\s*=\\s*(true|false)")
    private val forcePattern = Regex("force\\s*=\\s*(true|false)")

    override fun parse(text: String): DependencyModel {
        // Parse the base model first
        val baseModel = parseBaseModel(text)

        // Parse configuration options
        val config = parseConfiguration(text)

        // Create a new model with the updated config
        return baseModel.copy(config = config)
    }

    private fun parseBaseModel(text: String): DependencyModel {
        // Try all dependency patterns

        // Platform dependency
        platformPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact, version) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = version.trim('"', '\''),
                scope = configuration.trim(),
                type = "platform"
            )
        }

        // Enforced platform dependency
        enforcedPlatformPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact, version) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = version.trim('"', '\''),
                scope = configuration.trim(),
                type = "enforced-platform"
            )
        }

        // Project dependency
        projectPattern.find(text)?.let { matchResult ->
            val (configuration, path) = matchResult.destructured
            return DependencyModel(
                groupId = "project",
                artifactId = path.trim('"', '\''),
                version = null,
                scope = configuration.trim(),
                project = ProjectConfig(
                    path = path.trim('"', '\'')
                )
            )
        }

        // Map notation
        mapNotationPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact, version) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = version.trim('"', '\''),
                scope = configuration.trim()
            )
        }

        // Basic string notation with version
        basicStringPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact, version) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = version.trim('"', '\''),
                scope = configuration.trim()
            )
        }

        // Without version
        withoutVersionPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = null,
                scope = configuration.trim()
            )
        }

        throw IllegalArgumentException("Invalid Gradle Kotlin dependency format")
    }

    private fun parseConfiguration(text: String): DependencyConfig {
        // Parse exclusions
        val exclusions = exclusionPattern.findAll(text).map { matchResult ->
            val group = matchResult.groupValues[1]
            val module = if (matchResult.groupValues.size > 2 && matchResult.groupValues[2].isNotEmpty()) {
                matchResult.groupValues[2]
            } else {
                "*" // Wildcard for module if not specified
            }
            ExclusionModel(
                groupId = group.trim('"', '\''),
                artifactId = module.trim('"', '\'')
            )
        }.toList()

        // Parse other configuration options
        val transitive = transitivePattern.find(text)?.let { matchResult ->
            matchResult.groupValues[1] == "true"
        } ?: true

        val changing = changingPattern.find(text)?.let { matchResult ->
            matchResult.groupValues[1] == "true"
        } ?: false

        val force = forcePattern.find(text)?.let { matchResult ->
            matchResult.groupValues[1] == "true"
        } ?: false

        return DependencyConfig(
            transitive = transitive,
            changing = changing,
            force = force,
            exclusions = exclusions
        )
    }
}