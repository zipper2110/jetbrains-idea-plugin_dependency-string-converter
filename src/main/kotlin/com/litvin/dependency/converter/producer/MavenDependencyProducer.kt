package com.litvin.dependency.converter.producer

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class MavenDependencyProducer : DependencyProducer {
    override val targetFormat = DependencyFormat.MAVEN
    
    override fun produce(model: DependencyModel): String {
        val typeElement = when {
            // Non-default types always include a type element
            model.type != "jar" -> "    <type>${model.type}</type>\n"
            // Default "jar" type with no special handling
            else -> ""
        }
        
        // Handle scope element
        val scopeElement = if (model.scope != "implementation") {
            val mavenScope = mapGradleConfigurationToMavenScope(model.scope)
            "    <scope>$mavenScope</scope>\n"
        } else {
            ""
        }
        
        return """
            <dependency>
                <groupId>${model.groupId}</groupId>
                <artifactId>${model.artifactId}</artifactId>
                <version>${model.version}</version>
            ${typeElement}${scopeElement}</dependency>
        """.trimIndent()
    }

    
    private fun mapGradleConfigurationToMavenScope(gradleConfiguration: String): String {
        return when (gradleConfiguration) {
            "testImplementation" -> "test"
            "compileOnly" -> "provided"
            "runtimeOnly" -> "runtime"
            else -> "compile"
        }
    }
} 