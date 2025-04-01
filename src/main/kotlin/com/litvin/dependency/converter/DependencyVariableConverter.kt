package com.litvin.dependency.converter

class DependencyVariableConverter {
    fun convertMavenToGradle(mavenDependency: String, targetFormat: DependencyFormat): String {
        // Extract components from Maven XML
        val groupIdRegex = "<groupId>([^<]+)</groupId>".toRegex()
        val artifactIdRegex = "<artifactId>([^<]+)</artifactId>".toRegex()
        val versionRegex = "<version>([^<]+)</version>".toRegex()
        
        val groupIdMatch = groupIdRegex.find(mavenDependency)
        val artifactIdMatch = artifactIdRegex.find(mavenDependency)
        val versionMatch = versionRegex.find(mavenDependency)
        
        if (groupIdMatch == null || artifactIdMatch == null || versionMatch == null) {
            return mavenDependency
        }
        
        val groupId = convertMavenPropertyToGradleVariable(groupIdMatch.groupValues[1])
        val artifactId = convertMavenPropertyToGradleVariable(artifactIdMatch.groupValues[1])
        val version = convertMavenPropertyToGradleVariable(versionMatch.groupValues[1])
        
        // Get classifier and type if present
        val classifierMatch = "<classifier>([^<]+)</classifier>".toRegex().find(mavenDependency)
        val typeMatch = "<type>([^<]+)</type>".toRegex().find(mavenDependency)
        val classifier = classifierMatch?.groupValues?.get(1) ?: ""
        val type = typeMatch?.groupValues?.get(1) ?: ""
        
        // Determine configuration based on scope
        val scopeMatch = "<scope>([^<]+)</scope>".toRegex().find(mavenDependency)
        val scope = scopeMatch?.groupValues?.get(1) ?: "compile"
        
        val configuration = when (scope) {
            "test" -> "testImplementation"
            "runtime" -> "runtimeOnly"
            "provided" -> "compileOnly"
            else -> "implementation"
        }
        
        // Get comment if it exists
        val commentRegex = "<!-- (.+) -->".toRegex()
        val commentMatch = commentRegex.find(mavenDependency)
        val comment = commentMatch?.groupValues?.get(1) ?: ""
        
        // Extract exclusions if present
        val exclusions = mutableListOf<String>()
        val exclusionRegex = """<exclusion>\s*<groupId>([^<]+)</groupId>\s*<artifactId>([^<]+)</artifactId>\s*</exclusion>""".toRegex()
        exclusionRegex.findAll(mavenDependency).forEach { match ->
            val (excludeGroup, excludeModule) = match.destructured
            exclusions.add(if (targetFormat == DependencyFormat.GRADLE_KOTLIN) 
                "exclude(group = \"$excludeGroup\", module = \"$excludeModule\")"
            else 
                "exclude group: '$excludeGroup', module: '$excludeModule'"
            )
        }
        
        val isKotlinDSL = targetFormat == DependencyFormat.GRADLE_KOTLIN
        val quoteMark = if (isKotlinDSL) "\"" else "'"
        val commentLine = if (comment.isNotEmpty()) "// $comment\n" else ""
        
        val classifierPart = if (classifier.isNotEmpty()) ":$classifier" else ""
        val typePart = if (type.isNotEmpty()) "@$type" else ""
        val dependencyLine = if (isKotlinDSL) {
            "$configuration($quoteMark$groupId:$artifactId:$version$classifierPart$typePart$quoteMark)"
        } else {
            "$configuration $quoteMark$groupId:$artifactId:$version$classifierPart$typePart$quoteMark"
        }
        val exclusionsBlock = if (exclusions.isNotEmpty()) " {\n    ${exclusions.joinToString("\n    ")}\n}" else ""
        
        return "$commentLine$dependencyLine$exclusionsBlock"
    }
    
    fun convertGradleToMaven(gradleDependency: String, sourceFormat: DependencyFormat): String {
        // Extract components from Gradle format
        val regex = if (sourceFormat == DependencyFormat.GRADLE_KOTLIN) {
            """.*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s*\(["']([^:]+):([^:]+):([^'")\s]+)['"]\).*""".toRegex()
        } else {
            """.*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s+['"]([^:]+):([^:]+):([^'")\s]+)['"].*""".toRegex()
        }
        
        val matchResult = regex.find(gradleDependency) ?: return gradleDependency
        
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
    
    // For backward compatibility
    fun convertGradleToMaven(gradleDependency: String): String {
        val sourceFormat = if (gradleDependency.contains("implementation(")) {
            DependencyFormat.GRADLE_KOTLIN
        } else {
            DependencyFormat.GRADLE_GROOVY
        }
        return convertGradleToMaven(gradleDependency, sourceFormat)
    }
    
    // For backward compatibility methods
    fun convertMavenToGradle(mavenDependency: String, isKotlinDSL: Boolean): String {
        val targetFormat = if (isKotlinDSL) DependencyFormat.GRADLE_KOTLIN else DependencyFormat.GRADLE_GROOVY
        return convertMavenToGradle(mavenDependency, targetFormat)
    }
    
    private fun convertMavenPropertyToGradleVariable(mavenProperty: String): String {
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
    
    private fun convertSimpleMavenProperty(mavenProperty: String): String {
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
    
    /**
     * Converts dependency text from one format to another
     * 
     * @param text The dependency text to convert
     * @param sourceFormat The format of the input dependency
     * @param targetFormat The desired output format
     * @return The converted dependency text
     * @throws UnsupportedOperationException if the conversion between specified formats is not supported
     */
    fun convertDependency(text: String, sourceFormat: DependencyFormat, targetFormat: DependencyFormat): String {
        // Skip if formats are the same
        if (sourceFormat == targetFormat) {
            return text
        }
        
        return when {
            sourceFormat == DependencyFormat.MAVEN && 
                    (targetFormat == DependencyFormat.GRADLE_KOTLIN || targetFormat == DependencyFormat.GRADLE_GROOVY) -> {
                convertMavenToGradle(text, targetFormat)
            }
            
            (sourceFormat == DependencyFormat.GRADLE_KOTLIN || sourceFormat == DependencyFormat.GRADLE_GROOVY) && 
                    targetFormat == DependencyFormat.MAVEN -> {
                convertGradleToMaven(text, sourceFormat)
            }
            
            else -> throw UnsupportedOperationException("Conversion from $sourceFormat to $targetFormat is not supported")
        }
    }
} 