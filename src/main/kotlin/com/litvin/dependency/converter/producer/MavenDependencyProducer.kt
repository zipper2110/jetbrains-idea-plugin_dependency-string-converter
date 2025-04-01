package com.litvin.dependency.converter.producer

import com.litvin.dependency.converter.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class MavenDependencyProducer : DependencyProducer {
    override val targetFormat = DependencyFormat.MAVEN
    
    override fun produce(model: DependencyModel): String {
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
            ${scopeElement}</dependency>
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