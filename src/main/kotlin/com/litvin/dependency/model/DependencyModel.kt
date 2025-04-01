package com.litvin.dependency.model

/**
 * Internal representation of a dependency, used as the common format
 * for conversion between different dependency declaration formats.
 */
data class DependencyModel(
    val groupId: String,
    val artifactId: String,
    val version: String,
    val scope: String = "implementation",
    val type: String = "jar",
    val systemPath: String? = null,
    val classifier: String? = null,
    val optional: Boolean = false,
    val exclusions: List<ExclusionModel> = emptyList()
)

/**
 * Model representing a dependency exclusion
 */
data class ExclusionModel(
    val groupId: String,
    val artifactId: String
) 