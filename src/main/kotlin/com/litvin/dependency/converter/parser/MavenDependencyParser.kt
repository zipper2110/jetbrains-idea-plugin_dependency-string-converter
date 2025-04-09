package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.DependencyConfig
import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.ExclusionModel

class MavenDependencyParser : DependencyParser {
    override val supportedFormat = DependencyFormat.MAVEN
    
    // Patterns for individual elements
    private val groupIdPattern = "<groupId>(.*?)</groupId>"
    private val artifactIdPattern = "<artifactId>(.*?)</artifactId>"
    private val versionPattern = "<version>(.*?)</version>"
    private val typePattern = "<type>(.*?)</type>"
    private val classifierPattern = "<classifier>(.*?)</classifier>"
    private val scopePattern = "<scope>(.*?)</scope>"
    private val systemPathPattern = "<systemPath>(.*?)</systemPath>"
    private val optionalPattern = "<optional>(.*?)</optional>"
    private val exclusionsPattern = "<exclusions>(.*?)</exclusions>"
    
    private val exclusionPattern = Regex(
        "<exclusion>\\s*" +
        "<groupId>(.*?)</groupId>\\s*" +
        "<artifactId>(.*?)</artifactId>\\s*" +
        "</exclusion>",
        RegexOption.DOT_MATCHES_ALL
    )
    
    override fun parse(text: String): DependencyModel {
        // Extract required fields
        val groupId = extractValue(text, groupIdPattern)
            ?: throw IllegalArgumentException("Invalid Maven dependency format: missing groupId")
        val artifactId = extractValue(text, artifactIdPattern)
            ?: throw IllegalArgumentException("Invalid Maven dependency format: missing artifactId")
        val version = extractValue(text, versionPattern)
            ?: throw IllegalArgumentException("Invalid Maven dependency format: missing version")
        
        // Extract optional fields
        val type = extractValue(text, typePattern) ?: "jar"
        val classifier = extractValue(text, classifierPattern)
        val scopeValue = extractValue(text, scopePattern)
        val scope = if (scopeValue != null) mapMavenScopeToGradleConfiguration(scopeValue) else "implementation"
        val systemPath = extractValue(text, systemPathPattern)
        val optionalValue = extractValue(text, optionalPattern)
        val optional = optionalValue?.equals("true", ignoreCase = true) ?: false
        
        // Extract exclusions
        val exclusionsXml = extractValue(text, exclusionsPattern)
        val exclusions = if (exclusionsXml != null) {
            parseExclusions(exclusionsXml)
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
            config = DependencyConfig(exclusions = exclusions),
        )
    }
    
    private fun extractValue(text: String, pattern: String): String? {
        val regex = Regex(pattern, RegexOption.DOT_MATCHES_ALL)
        val matchResult = regex.find(text)
        return matchResult?.groupValues?.get(1)?.trim()
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