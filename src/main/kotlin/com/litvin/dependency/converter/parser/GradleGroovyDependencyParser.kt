package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.*

class GradleGroovyDependencyParser : DependencyParser {
    override val supportedFormat = DependencyFormat.GRADLE_GROOVY
    
    // Pattern for dependencies with version
    private val withVersionPattern = Regex(
        "(\\w+)\\s+[\"']([^:]+):([^:]+):([^\"']+)[\"']"
    )
    
    // Pattern for dependencies with parentheses and version
    private val withParenthesesVersionPattern = Regex(
        "(\\w+)\\s*\\([\"']([^:]+):([^:]+):([^\"']+)[\"']\\)"
    )
    
    // Pattern for dependencies without version
    private val withoutVersionPattern = Regex(
        "(\\w+)\\s+[\"']([^:]+):([^:]+)[\"']"
    )
    
    // Pattern for map notation
    private val mapNotationPattern = Regex(
        "(\\w+)\\s+group:\\s*[\"']([^\"']+)[\"'],\\s*name:\\s*[\"']([^\"']+)[\"'],\\s*version:\\s*[\"']([^\"']+)[\"']"
    )
    
    // Pattern for dependencies with platform
    private val platformPattern = Regex(
        "(\\w+)\\s*\\(platform\\([\"']([^:]+):([^:]+):([^\"']+)[\"']\\)\\)"
    )
    
    // Pattern for dependencies with enforced platform
    private val enforcedPlatformPattern = Regex(
        "(\\w+)\\s*\\(enforcedPlatform\\([\"']([^:]+):([^:]+):([^\"']+)[\"']\\)\\)"
    )
    
    // Pattern for project dependencies
    private val projectPattern = Regex(
        "(\\w+)\\s+project\\([\"']([^\"']+)[\"']\\)"
    )
    
    // Pattern for project dependencies with parentheses
    private val projectParenthesesPattern = Regex(
        "(\\w+)\\s*\\(project\\([\"']([^\"']+)[\"']\\)\\)"
    )
    
    // Pattern for project dependencies with configuration
    private val projectWithConfigPattern = Regex(
        "(\\w+)\\s+project\\(path:\\s*[\"']([^\"']+)[\"'],\\s*configuration:\\s*[\"']([^\"']+)[\"']\\)"
    )
    
    // Pattern for project dependencies with configuration in parentheses
    private val projectWithConfigParenthesesPattern = Regex(
        "(\\w+)\\s*\\(project\\(path:\\s*[\"']([^\"']+)[\"'],\\s*configuration:\\s*[\"']([^\"']+)[\"']\\)\\)"
    )
    
    // Pattern for file dependencies
    private val filePattern = Regex(
        "(\\w+)\\s+files?\\([\"']([^\"']+)[\"']\\)"
    )
    
    // Pattern for file tree dependencies
    private val fileTreePattern = Regex(
        "(\\w+)\\s+fileTree\\([\"']([^\"']+)[\"']\\)"
    )
    
    // Pattern for dependencies with classifier
    private val classifierPattern = Regex(
        "(\\w+)\\s+[\"']([^:]+):([^:]+):([^:]+):([^\"']+)[\"']"
    )
    
    // Patterns for configuration blocks
    private val transitivePattern = Regex("transitive\\s*=\\s*(true|false)")
    private val changingPattern = Regex("changing\\s*=\\s*(true|false)")
    private val forcePattern = Regex("force\\s*=\\s*(true|false)")
    private val excludePattern = Regex("exclude\\s+group:\\s*[\"']([^\"']+)[\"'](?:,\\s*module:\\s*[\"']([^\"']+)[\"'])?")
    private val attributePattern = Regex("attribute\\s*\\(([^,]+),\\s*[\"']?([^\\)\"']+)[\"']?\\)")
    private val capabilityPattern = Regex("requireCapability\\s*\\([\"']([^:\"']+)(?::([^:\"']+)(?::([^\"']+))?)?[\"']\\)")
    
    override fun parse(text: String): DependencyModel {
        // Parse the base model first
        val baseModel = parseBaseModel(text)
        
        // Parse configuration options
        val transitive = transitivePattern.find(text)?.let { matchResult ->
            matchResult.groupValues[1] == "true"
        } ?: true
        
        val changing = changingPattern.find(text)?.let { matchResult ->
            matchResult.groupValues[1] == "true"
        } ?: false
        
        val force = forcePattern.find(text)?.let { matchResult ->
            matchResult.groupValues[1] == "true"
        } ?: false
        
        // Parse exclusions
        val exclusions = excludePattern.findAll(text).map { matchResult ->
            val group = matchResult.groupValues[1]
            val module = if (matchResult.groupValues.size > 2 && matchResult.groupValues[2].isNotEmpty()) {
                matchResult.groupValues[2]
            } else {
                group
            }
            ExclusionModel(
                groupId = group.trim('"', '\''),
                artifactId = module.trim('"', '\'')
            )
        }.toList()
        
        // Parse attributes
        val attributes = attributePattern.findAll(text).map { matchResult ->
            val (key, value) = matchResult.destructured
            AttributeModel(
                key = key.trim(),
                value = value.trim()
            )
        }.toList()
        
        // Parse capabilities
        val capabilities = capabilityPattern.findAll(text).map { matchResult ->
            val group = matchResult.groupValues[1]
            val name = if (matchResult.groupValues.size > 2 && matchResult.groupValues[2].isNotEmpty()) {
                matchResult.groupValues[2]
            } else {
                group
            }
            val version = if (matchResult.groupValues.size > 3 && matchResult.groupValues[3].isNotEmpty()) {
                matchResult.groupValues[3]
            } else {
                "" // Default empty version if not specified
            }
            CapabilityModel(
                group = group.trim('"', '\''),
                name = name.trim('"', '\''),
                version = version.trim('"', '\'')
            )
        }.toList()
        
        // Update the model with the configuration
        val config = DependencyConfig(
            transitive = transitive,
            changing = changing,
            force = force,
            exclusions = exclusions,
            attributes = attributes,
            capabilities = capabilities
        )
        
        // Create a new model with the updated config
        return baseModel.copy(config = config)
    }

    private fun parseBaseModel(text: String): DependencyModel {
        // Try all dependency patterns
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
        
        // Check for project with configuration in parentheses
        projectWithConfigParenthesesPattern.find(text)?.let { matchResult ->
            val (configuration, path, targetConfig) = matchResult.destructured
            return DependencyModel(
                groupId = "project",
                artifactId = path.trim('"', '\''),
                scope = configuration.trim(),
                project = ProjectConfig(
                    path = path.trim('"', '\''),
                    targetConfiguration = targetConfig.trim('"', '\'')
                )
            )
        }
        
        // Check for project with configuration
        projectWithConfigPattern.find(text)?.let { matchResult ->
            val (configuration, path, targetConfig) = matchResult.destructured
            return DependencyModel(
                groupId = "project",
                artifactId = path.trim('"', '\''),
                scope = configuration.trim(),
                project = ProjectConfig(
                    path = path.trim('"', '\''),
                    targetConfiguration = targetConfig.trim('"', '\'')
                )
            )
        }
        
        // Check for project with parentheses
        projectParenthesesPattern.find(text)?.let { matchResult ->
            val (configuration, path) = matchResult.destructured
            return DependencyModel(
                groupId = "project",
                artifactId = path.trim('"', '\''),
                scope = configuration.trim(),
                project = ProjectConfig(
                    path = path.trim('"', '\'')
                )
            )
        }
        
        // Check for basic project
        projectPattern.find(text)?.let { matchResult ->
            val (configuration, path) = matchResult.destructured
            return DependencyModel(
                groupId = "project",
                artifactId = path.trim('"', '\''),
                scope = configuration.trim(),
                project = ProjectConfig(
                    path = path.trim('"', '\'')
                )
            )
        }
        
        // Check for file tree dependencies
        fileTreePattern.find(text)?.let { matchResult ->
            val (configuration, path) = matchResult.destructured
            return DependencyModel(
                groupId = "file",
                artifactId = path.trim('"', '\''),
                scope = configuration.trim(),
                file = FileConfig(
                    type = FileType.FILE_TREE,
                    path = path.trim('"', '\'')
                )
            )
        }
        
        // Check for file dependencies (this can be single files, directories, or URLs)
        filePattern.find(text)?.let { matchResult ->
            val (configuration, path) = matchResult.destructured
            val trimmedPath = path.trim('"', '\'')
            val fileType = when {
                trimmedPath.startsWith("http://") || trimmedPath.startsWith("https://") -> FileType.URL
                !trimmedPath.contains(".") -> FileType.DIRECTORY
                else -> FileType.SINGLE_FILE
            }
            
            return DependencyModel(
                groupId = "file",
                artifactId = trimmedPath,
                scope = configuration.trim(),
                file = FileConfig(
                    type = fileType,
                    path = trimmedPath
                )
            )
        }
        
        // Check for dependencies with classifier
        classifierPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact, version, classifier) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = version.trim('"', '\''),
                scope = configuration.trim(),
                classifier = classifier.trim('"', '\'')
            )
        }
        
        // Check for map notation
        mapNotationPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact, version) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = version.trim('"', '\''),
                scope = configuration.trim()
            )
        }
        
        withParenthesesVersionPattern.find(text)?.let { matchResult ->
            val (configuration, group, artifact, version) = matchResult.destructured
            return DependencyModel(
                groupId = group.trim('"', '\''),
                artifactId = artifact.trim('"', '\''),
                version = version.trim('"', '\''),
                scope = configuration.trim()
            )
        }
        
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