package com.litvin.dependency.converter.impl

import com.litvin.dependency.converter.DependencyFormat

class GradleToMavenConverter(private val sourceFormat: DependencyFormat) : DependencyConversionStrategy {
    override fun supports(sourceFormat: DependencyFormat, targetFormat: DependencyFormat): Boolean {
        return (sourceFormat == DependencyFormat.GRADLE_KOTLIN || sourceFormat == DependencyFormat.GRADLE_GROOVY) &&
               targetFormat == DependencyFormat.MAVEN
    }

    override fun convert(text: String): String {
        // Extract components from Gradle format
        val regex = if (sourceFormat == DependencyFormat.GRADLE_KOTLIN) {
            """.*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s*\(["']([^:]+):([^:]+):([^'")\s]+)['"]\).*""".toRegex()
        } else {
            """.*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s+['"]([^:]+):([^:]+):([^'")\s]+)['"].*""".toRegex()
        }
        
        val matchResult = regex.find(text) ?: return text
        
        val (group, artifact, version) = matchResult.destructured
        
        val convertedGroup = convertGradleVariableToMavenProperty(group.trim('"'))
        val convertedArtifact = convertGradleVariableToMavenProperty(artifact)
        val convertedVersion = convertGradleVariableToMavenProperty(version)
        
        return """
            <dependency>
                <groupId>$convertedGroup</groupId>
                <artifactId>$convertedArtifact</artifactId>
                <version>$convertedVersion</version>
            </dependency>
        """.trimIndent()
    }

    private fun convertGradleVariableToMavenProperty(gradleVariable: String): String {
        if (!gradleVariable.contains("\${")) {
            return gradleVariable
        }
        
        // Handle nested variables
        if (gradleVariable.contains(".\${")) {
            return gradleVariable.replace("\\$\\{([^}]+)}".toRegex()) { match ->
                val property = match.groupValues[1]
                if (property.contains(".")) {
                    // Handle nested properties like ${springBoot}.${env}.version
                    val parts = property.split(".")
                    val first = parts.first()
                    val rest = parts.drop(1)
                    "\${" + convertSimpleGradleVariable(first) + "." + rest.joinToString(".") + "}"
                } else {
                    "\${" + convertSimpleGradleVariable(property) + "}"
                }
            }
        }
        
        return "\${" + convertSimpleGradleVariable(gradleVariable.removeSurrounding("\${", "}")) + "}"
    }
    
    private fun convertSimpleGradleVariable(gradleVariable: String): String {
        // Special case for project.* properties
        if (gradleVariable.matches(Regex("project\\.[a-zA-Z]+"))) {
            return gradleVariable
        }
        
        // Special case for springBootVersion -> spring-boot.version
        if (gradleVariable == "springBootVersion") {
            return "spring-boot.version"
        }
        
        // Special case for springBoot -> spring-boot
        if (gradleVariable == "springBoot") {
            return "spring-boot"
        }
        
        // Standard variables $springBootVersion -> ${spring-boot.version}
        return gradleVariable.replace(Regex("([a-z])([A-Z])"), "$1-$2").lowercase()
    }
}

