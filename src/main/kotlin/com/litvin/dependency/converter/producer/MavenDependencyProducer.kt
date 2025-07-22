package com.litvin.dependency.converter.producer

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class MavenDependencyProducer : DependencyProducer {
    override val targetFormat = DependencyFormat.MAVEN
    
    override fun produce(model: DependencyModel): String {
        val versionElement = model.version?.let { "    <version>$it</version>" }

        // Handle type element - only include for non-jar types
        val typeElement = when {
            model.type == "jar" -> null
            else -> "    <type>${model.type}</type>"
        }
        
        // Handle classifier element
        val classifierElement = model.classifier?.let { "    <classifier>${it}</classifier>" }
        
        // Handle scope element
        val mavenScope = mapGradleConfigurationToMavenScope(model.scope)
        val scopeElement = if (model.systemPath != null) {
            // System path requires system scope
            "    <scope>system</scope>"
        } else if (mavenScope == "compile") {
            null
        } else {
            "    <scope>$mavenScope</scope>"
        }
        
        // Handle systemPath element if present
        val systemPathElement = model.systemPath?.let { "    <systemPath>${it}</systemPath>" }
        
        // Handle optional element if present
        val optionalElement = when {
            model.optional -> "    <optional>true</optional>"
            else -> null
        }
        
        // Handle exclusions element if present
        val exclusionsElement = if (model.config.exclusions.isNotEmpty()) {
            val exclusionsList = model.config.exclusions.joinToString("") { exclusion ->
                """
                <exclusion>
                    <groupId>${exclusion.groupId}</groupId>
                    <artifactId>${exclusion.artifactId}</artifactId>
                </exclusion>""".trimIndent()
            }
            """
            <exclusions>
                $exclusionsList
            </exclusions>
            """.trimIndent()
        } else {
            null
        }
        
        return buildString {
            appendLine("<dependency>")
            appendLine("    <groupId>${model.groupId}</groupId>")
            appendLine("    <artifactId>${model.artifactId}</artifactId>")
            versionElement?.let { appendLine(it) }
            typeElement?.let { appendLine(it) }
            classifierElement?.let { appendLine(it) }
            scopeElement?.let { appendLine(it) }
            systemPathElement?.let { appendLine(it) }
            optionalElement?.let { appendLine(it) }
            exclusionsElement?.let { appendLine(it) }
            append("</dependency>")
        }
    }
    
    private fun mapGradleConfigurationToMavenScope(gradleConfiguration: String?): String? {
        return when (gradleConfiguration) {
            "implementation" -> "compile"
            "testImplementation" -> "test"
            "compileOnly" -> "provided"
            "runtimeOnly" -> "runtime"
            else -> gradleConfiguration
        }
    }
} 