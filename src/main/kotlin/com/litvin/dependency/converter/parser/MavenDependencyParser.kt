package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.ExclusionModel

class MavenDependencyParser : DependencyParser {
    override val supportedFormat = DependencyFormat.MAVEN
    
    private val mavenDependencyPattern = Regex(
        "<dependency>\\s*" +
        "<groupId>(.*?)</groupId>\\s*" +
        "<artifactId>(.*?)</artifactId>\\s*" +
        "<version>(.*?)</version>\\s*" +
        "(?:<type>(.*?)</type>\\s*)?" +
        "(?:<classifier>(.*?)</classifier>\\s*)?" +
        "(?:<scope>(.*?)</scope>\\s*)?" +
        "(?:<systemPath>(.*?)</systemPath>\\s*)?" +
        "(?:<optional>(.*?)</optional>\\s*)?" +
        "(?:<exclusions>(.*?)</exclusions>\\s*)?" +
        "</dependency>",
        RegexOption.DOT_MATCHES_ALL
    )
    
    private val exclusionPattern = Regex(
        "<exclusion>\\s*" +
        "<groupId>(.*?)</groupId>\\s*" +
        "<artifactId>(.*?)</artifactId>\\s*" +
        "</exclusion>",
        RegexOption.DOT_MATCHES_ALL
    )
    
    override fun parse(text: String): DependencyModel {
        val matchResult = mavenDependencyPattern.find(text)
            ?: throw IllegalArgumentException("Invalid Maven dependency format")
        
        val groupValues = matchResult.groupValues
        val groupId = groupValues[1].trim()
        val artifactId = groupValues[2].trim()
        val version = groupValues[3].trim()
        val type = if (groupValues.size > 4 && groupValues[4].isNotEmpty()) {
            groupValues[4].trim()
        } else {
            "jar" // Default type is jar
        }
        val classifier = if (groupValues.size > 5 && groupValues[5].isNotEmpty()) {
            groupValues[5].trim()
        } else {
            null
        }
        val scope = if (groupValues.size > 6 && groupValues[6].isNotEmpty()) {
            mapMavenScopeToGradleConfiguration(groupValues[6].trim())
        } else {
            "implementation"
        }
        val systemPath = if (groupValues.size > 7 && groupValues[7].isNotEmpty()) {
            groupValues[7].trim()
        } else {
            null
        }
        val optional = if (groupValues.size > 8 && groupValues[8].isNotEmpty()) {
            groupValues[8].trim().equals("true", ignoreCase = true)
        } else {
            false
        }
        
        val exclusions = if (groupValues.size > 9 && groupValues[9].isNotEmpty()) {
            parseExclusions(groupValues[9])
        } else {
            emptyList()
        }
        
        return DependencyModel(
            groupId = groupId,
            artifactId = artifactId,
            version = version,
            scope = scope,
            type = type,
            systemPath = systemPath,
            classifier = classifier,
            optional = optional,
            exclusions = exclusions
        )
    }
    
    private fun parseExclusions(exclusionsXml: String): List<ExclusionModel> {
        val exclusions = mutableListOf<ExclusionModel>()
        exclusionPattern.findAll(exclusionsXml).forEach { matchResult ->
            val exGroupId = matchResult.groupValues[1].trim()
            val exArtifactId = matchResult.groupValues[2].trim()
            exclusions.add(ExclusionModel(exGroupId, exArtifactId))
        }
        return exclusions
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