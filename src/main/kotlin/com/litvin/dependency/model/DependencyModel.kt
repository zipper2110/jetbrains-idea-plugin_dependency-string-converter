package com.litvin.dependency.model

/**
 * Internal representation of a dependency, used as the common format
 * for conversion between different dependency declaration formats.
 */
data class DependencyModel(
    val groupId: String,
    val artifactId: String,
    val version: String,
    val scope: String = "implementation"
) 