package com.litvin.dependency.converter.producer

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class MavenDependencyProducer : DependencyProducer {
    override val targetFormat = DependencyFormat.MAVEN
    
    override fun produce(model: DependencyModel): String {
        // Handle type element - only include for non-jar types
        val typeElement = when {
            model.type != "jar" -> "    <type>${model.type}</type>\n"
            else -> ""
        }
        
        // Handle classifier element
        val classifierElement = if (model.classifier != null) {
            "    <classifier>${model.classifier}</classifier>\n"
        } else {
            ""
        }
        
        // Handle scope element
        val mavenScope = mapGradleConfigurationToMavenScope(model.scope)
        val scopeElement = if (model.systemPath != null) {
            // System path requires system scope
            "    <scope>system</scope>\n"
        } else if (mavenScope != "compile") {
            // Only include scope element if not the default "compile" scope
            "    <scope>$mavenScope</scope>\n"
        } else {
            ""
        }
        
        // Handle systemPath element if present
        val systemPathElement = if (model.systemPath != null) {
            "    <systemPath>${model.systemPath}</systemPath>\n"
        } else {
            ""
        }
        
        // Handle optional element if present
        val optionalElement = if (model.optional) {
            "    <optional>true</optional>\n"
        } else {
            ""
        }
        
        // Handle exclusions element if present
        val exclusionsElement = if (model.exclusions.isNotEmpty()) {
            val exclusionsList = model.exclusions.joinToString("\n") { exclusion ->
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
            ""
        }
        
        return """
            <dependency>
                <groupId>${model.groupId}</groupId>
                <artifactId>${model.artifactId}</artifactId>
                <version>${model.version}</version>
            ${typeElement}${classifierElement}${scopeElement}${systemPathElement}${optionalElement}${exclusionsElement}</dependency>
        """.trimIndent()
    }
    
    private fun mapGradleConfigurationToMavenScope(gradleConfiguration: String): String {
        return when (gradleConfiguration) {
            "implementation" -> "compile"
            "testImplementation" -> "test"
            "compileOnly" -> "provided"
            "runtimeOnly" -> "runtime"
            else -> "compile" // Default fallback
        }
    }
} 