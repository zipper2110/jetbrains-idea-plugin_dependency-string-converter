package com.litvin.dependency.converter.parser

import com.litvin.dependency.converter.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class MavenDependencyParser : DependencyParser {
    override val supportedFormat = DependencyFormat.MAVEN
    
    private val mavenDependencyPattern = Regex(
        "<dependency>\\s*" +
        "<groupId>(.*?)</groupId>\\s*" +
        "<artifactId>(.*?)</artifactId>\\s*" +
        "<version>(.*?)</version>\\s*" +
        "(?:<scope>(.*?)</scope>\\s*)?" +
        "</dependency>",
        RegexOption.DOT_MATCHES_ALL
    )
    
    override fun parse(text: String): DependencyModel {
        val matchResult = mavenDependencyPattern.find(text)
            ?: throw IllegalArgumentException("Invalid Maven dependency format")
        
        val groupValues = matchResult.groupValues
        val groupId = groupValues[1].trim()
        val artifactId = groupValues[2].trim()
        val version = groupValues[3].trim()
        val scope = if (groupValues.size > 4 && groupValues[4].isNotEmpty()) {
            mapMavenScopeToGradleConfiguration(groupValues[4].trim())
        } else {
            "implementation"
        }
        
        return DependencyModel(
            groupId = groupId,
            artifactId = artifactId,
            version = version,
            scope = scope
        )
    }
    
    private fun mapMavenScopeToGradleConfiguration(mavenScope: String): String {
        return when (mavenScope) {
            "test" -> "testImplementation"
            "provided" -> "compileOnly"
            "runtime" -> "runtimeOnly"
            else -> "implementation"
        }
    }
} 