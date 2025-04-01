package com.litvin.dependency.converter.impl

import com.litvin.dependency.converter.DependencyFormat

abstract class MavenToGradleBaseConverter : DependencyConversionStrategy {
    protected abstract val targetFormat: DependencyFormat

    protected fun convertMavenPropertyToGradleVariable(mavenProperty: String): String {
        if (!mavenProperty.contains("\${")) {
            return mavenProperty
        }

        // Handle nested properties
        if (mavenProperty.contains("\${") && mavenProperty.contains(".\${")) {
            val parts = mavenProperty.split(".\${")
            val prefix = parts[0].removeSurrounding("\${", "}")
            val suffix = parts[1]

            return "\${" + convertSimpleMavenProperty(prefix) + "}.\${" + suffix
        }

        // Handle simple properties
        return mavenProperty.replace("\\$\\{([^}]+)}".toRegex()) { match ->
            "\${" + convertSimpleMavenProperty(match.groupValues[1]) + "}"
        }
    }

    protected fun convertSimpleMavenProperty(mavenProperty: String): String {
        // Special case for project.* properties
        if (mavenProperty.matches(Regex("project\\.[a-zA-Z]+"))) {
            return mavenProperty
        }

        // Special case for spring-boot.version
        if (mavenProperty == "spring-boot.version") {
            return "springBootVersion"
        }

        // Special case for spring-boot
        if (mavenProperty == "spring-boot") {
            return "springBoot"
        }

        // Handle nested variables
        if (mavenProperty.contains(".\${")) {
            val parts = mavenProperty.split(".\${")
            val prefix = parts[0]
            val suffix = parts[1]
            return convertSimpleMavenProperty(prefix) + ".\${" + suffix
        }

        // Standard property conversion ${property-name} -> $propertyName
        return mavenProperty.replace("-", "").let { prop ->
            if (prop.contains(".")) {
                val parts = prop.split(".")
                if (parts.size == 2 && parts[1] == "version") {
                    parts[0] + parts[1].replaceFirstChar { it.uppercase() }
                } else {
                    prop
                }
            } else {
                prop
            }
        }
    }
}