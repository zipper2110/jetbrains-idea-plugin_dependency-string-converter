package com.litvin.dependency.converter.producer

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.FileType

class GradleGroovyDependencyProducer : DependencyProducer {
    override val targetFormat = DependencyFormat.GRADLE_GROOVY
    
    override fun produce(model: DependencyModel): String {
        val coordinates = if (model.version != null) {
            "${model.groupId}:${model.artifactId}:${model.version}"
        } else {
            "${model.groupId}:${model.artifactId}"
        }
        
        // Add classifier if present
        val coordinatesWithClassifier = if (model.classifier != null) {
            "$coordinates:${model.classifier}"
        } else {
            coordinates
        }
        
        // Handle constraint dependencies
        if (model.constraint != null) {
            val constraintBuilder = StringBuilder("dependencies {\n")
            constraintBuilder.append("    constraints {\n")
            
            // Basic constraint with reason
            constraintBuilder.append("        ${model.scope}('$coordinatesWithClassifier')")
            
            // Check if there are additional configurations
            if (hasConstraintConfiguration(model)) {
                constraintBuilder.append(" {\n")
                
                // Add reason if present
                if (model.constraint.reason != null) {
                    constraintBuilder.append("            because '${model.constraint.reason}'\n")
                }
                
                // Add rejected versions if present
                for (rejectVersion in model.constraint.rejectVersions) {
                    constraintBuilder.append("            reject '$rejectVersion'\n")
                }
                
                // Add other configurations like capabilities, attributes, etc.
                if (hasConfiguration(model)) {
                    val config = generateConfig(model)
                    // Adjust indentation for nested configuration
                    val adjustedConfig = config.replace("{\n", "{\n    ")
                        .replace("\n    ", "\n            ")
                        .replace("\n}", "\n        }")
                    
                    // Remove the opening brace and first newline since we already started the config block
                    val configContent = adjustedConfig.substring(adjustedConfig.indexOf("{") + 1)
                    constraintBuilder.append(configContent)
                } else {
                    constraintBuilder.append("        }\n")
                }
            } else {
                // If only reason exists, add simple because block
                if (model.constraint.reason != null) {
                    constraintBuilder.append(" {\n")
                    constraintBuilder.append("            because '${model.constraint.reason}'\n")
                    constraintBuilder.append("        }\n")
                } else {
                    constraintBuilder.append("\n")
                }
            }
            
            constraintBuilder.append("    }\n")
            constraintBuilder.append("}")
            
            return constraintBuilder.toString()
        }
        
        // Handle bundle dependencies
        if (model.bundle != null) {
            val bundleBuilder = StringBuilder()
            
            // Main bundle dependency
            bundleBuilder.append("${model.scope}('$coordinatesWithClassifier')")
            
            // Configuration blocks
            if (hasConfiguration(model) || model.bundle.dependencies.isNotEmpty()) {
                bundleBuilder.append(" {\n")
                
                // Generate standard configuration
                if (hasConfiguration(model)) {
                    val configBuilder = StringBuilder()
                    
                    // Handle transitive flag
                    if (model.config.transitive != true) {
                        configBuilder.append("    transitive = false\n")
                    }
                    
                    // Handle changing flag
                    if (model.config.changing) {
                        configBuilder.append("    changing = true\n")
                    }
                    
                    // Handle force flag
                    if (model.config.force) {
                        configBuilder.append("    force = true\n")
                    }
                    
                    // Handle exclusions
                    for (exclusion in model.config.exclusions) {
                        configBuilder.append("    exclude group: '${exclusion.groupId}', module: '${exclusion.artifactId}'\n")
                    }
                    
                    // Handle attributes
                    if (model.config.attributes.isNotEmpty()) {
                        configBuilder.append("    attributes {\n")
                        for (attribute in model.config.attributes) {
                            configBuilder.append("        attribute(${attribute.key}, ${attribute.value})\n")
                        }
                        configBuilder.append("    }\n")
                    }
                    
                    // Handle capabilities
                    if (model.config.capabilities.isNotEmpty()) {
                        configBuilder.append("    capabilities {\n")
                        for (capability in model.config.capabilities) {
                            configBuilder.append("        requireCapability('${capability.group}:${capability.name}:${capability.version}')\n")
                        }
                        configBuilder.append("    }\n")
                    }
                    
                    bundleBuilder.append(configBuilder)
                }
                
                // Handle bundle dependencies
                if (model.bundle.dependencies.isNotEmpty()) {
                    bundleBuilder.append("    dependencies {\n")
                    for (bundleDep in model.bundle.dependencies) {
                        val depCoordinates = if (bundleDep.version != null) {
                            "${bundleDep.groupId}:${bundleDep.artifactId}:${bundleDep.version}"
                        } else {
                            "${bundleDep.groupId}:${bundleDep.artifactId}"
                        }
                        
                        bundleBuilder.append("        module('$depCoordinates')")
                        
                        if (!bundleDep.transitive) {
                            bundleBuilder.append(" {\n            transitive = false\n        }")
                        }
                        
                        bundleBuilder.append("\n")
                    }
                    bundleBuilder.append("    }\n")
                }
                
                bundleBuilder.append("}")
            }
            
            return bundleBuilder.toString()
        }
        
        // Handle platform and enforced-platform types
        if (model.type == "platform") {
            return "${model.scope}(platform('$coordinatesWithClassifier'))"
        } else if (model.type == "enforced-platform") {
            return "${model.scope}(enforcedPlatform('$coordinatesWithClassifier'))"
        }
        
        // Handle module dependencies
        if (model.module != null) {
            return "${model.scope}(module('$coordinatesWithClassifier'))" + generateConfig(model)
        }
        
        // Handle project dependencies
        if (model.project != null) {
            val projectPath = model.project.path
            
            if (model.project.targetConfiguration != null) {
                val projectWithConfig = "project(path: '$projectPath', configuration: '${model.project.targetConfiguration}')"
                return "${model.scope}($projectWithConfig)" + generateConfig(model)
            } else {
                return "${model.scope} project('$projectPath')" + generateConfig(model)
            }
        }
        
        // Handle file dependencies
        if (model.file != null) {
            when (model.file.type) {
                FileType.SINGLE_FILE -> {
                    return "${model.scope}(files('${model.file.path}'))" + generateConfig(model)
                }
                FileType.FILE_TREE -> {
                    val fileTree = StringBuilder("fileTree('${model.file.path}')")
                    if (model.file.includes.isNotEmpty()) {
                        fileTree.append(" {\n")
                        for (include in model.file.includes) {
                            fileTree.append("    include '$include'\n")
                        }
                        fileTree.append("}")
                    }
                    return "${model.scope}($fileTree)" + generateConfig(model)
                }
                FileType.DIRECTORY -> {
                    return "${model.scope}(dir('${model.file.path}'))" + generateConfig(model)
                }
                FileType.URL -> {
                    return "${model.scope}(url('${model.file.path}'))" + generateConfig(model)
                }
            }
        }
        
        // Handle regular dependencies with configurations
        if (hasConfiguration(model)) {
            return "${model.scope}('$coordinatesWithClassifier')" + generateConfig(model)
        }
        
        // Basic dependency
        return "${model.scope} '$coordinatesWithClassifier'"
    }
    
    private fun hasConfiguration(model: DependencyModel): Boolean {
        return model.config.transitive != true ||
               model.config.changing ||
               model.config.force ||
               model.config.exclusions.isNotEmpty() ||
               model.config.attributes.isNotEmpty() ||
               model.config.capabilities.isNotEmpty()
    }
    
    private fun hasConstraintConfiguration(model: DependencyModel): Boolean {
        return model.constraint?.rejectVersions?.isNotEmpty() == true ||
               hasConfiguration(model)
    }
    
    private fun generateConfig(model: DependencyModel): String {
        val config = model.config
        val builder = StringBuilder(" {\n")
        
        // Handle transitive flag
        if (config.transitive != true) {
            builder.append("    transitive = false\n")
        }
        
        // Handle changing flag
        if (config.changing) {
            builder.append("    changing = true\n")
        }
        
        // Handle force flag
        if (config.force) {
            builder.append("    force = true\n")
        }
        
        // Handle exclusions
        for (exclusion in config.exclusions) {
            builder.append("    exclude group: '${exclusion.groupId}', module: '${exclusion.artifactId}'\n")
        }
        
        // Handle attributes
        if (config.attributes.isNotEmpty()) {
            builder.append("    attributes {\n")
            for (attribute in config.attributes) {
                if (attribute.value.matches(Regex("\\d+"))) {
                    // Handle numeric attributes
                    builder.append("        attribute(${attribute.key}, ${attribute.value})\n")
                } else {
                    // Handle named attributes
                    val parts = attribute.value.split(".")
                    val typeName = parts[0]
                    val valueName = attribute.value
                    builder.append("        attribute(${attribute.key}, project.objects.named($typeName, $valueName))\n")
                }
            }
            builder.append("    }\n")
        }
        
        // Handle capabilities
        if (config.capabilities.isNotEmpty()) {
            builder.append("    capabilities {\n")
            for (capability in config.capabilities) {
                builder.append("        requireCapability('${capability.group}:${capability.name}:${capability.version}')\n")
            }
            builder.append("    }\n")
        }
        
        builder.append("}")
        return builder.toString()
    }
} 