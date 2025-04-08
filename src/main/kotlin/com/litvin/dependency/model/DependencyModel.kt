package com.litvin.dependency.model

/**
 * Internal representation of a dependency, used as the common format
 * for conversion between different dependency declaration formats.
 */
data class DependencyModel(
    // Core dependency coordinates
    val groupId: String,
    val artifactId: String,
    val version: String? = null,
    val scope: String? = null,

    // Basic dependency metadata
    val type: String? = null,
    val classifier: String? = null,
    val optional: Boolean = false,

    // Dependency configurations
    val config: DependencyConfig = DependencyConfig(),

    // Project dependency specific
    val project: ProjectConfig? = null,

    // File dependency specific
    val file: FileConfig? = null,

    // Module dependency specific
    val module: ModuleConfig? = null,

    // Bundle dependency specific
    val bundle: BundleConfig? = null,

    // Constraint specific
    val constraint: ConstraintConfig? = null
)

/**
 * Common dependency configurations that can be applied to any dependency type
 */
data class DependencyConfig(
    val transitive: Boolean = true,
    val changing: Boolean = false,
    val force: Boolean = false,
    val exclusions: List<ExclusionModel> = emptyList(),
    val attributes: List<AttributeModel> = emptyList(),
    val capabilities: List<CapabilityModel> = emptyList(),
    val targetConfiguration: String? = null
)

/**
 * Project dependency specific configuration
 */
data class ProjectConfig(
    val path: String,
    val targetConfiguration: String? = null
)

/**
 * File dependency specific configuration
 */
data class FileConfig(
    val type: FileType,
    val path: String,
    val includes: List<String> = emptyList(),
    val excludes: List<String> = emptyList(),
    val builtBy: List<String> = emptyList()
)

/**
 * Module dependency specific configuration
 */
data class ModuleConfig(
    val name: String? = null,
    val substitutions: List<ModuleModel> = emptyList(),
    val dependencies: List<ModuleDependencyModel> = emptyList()
)

/**
 * Bundle dependency specific configuration
 */
data class BundleConfig(
    val dependencies: List<BundleModel> = emptyList()
)

/**
 * Constraint specific configuration
 */
data class ConstraintConfig(
    val rejectVersions: List<String> = emptyList(),
    val reason: String? = null
)

/**
 * Type of file dependency
 */
enum class FileType {
    SINGLE_FILE,
    FILE_TREE,
    DIRECTORY,
    URL
}

/**
 * Model representing a dependency exclusion
 */
data class ExclusionModel(
    val groupId: String,
    val artifactId: String
)

/**
 * Model representing a dependency attribute
 */
data class AttributeModel(
    val key: String,
    val value: String
)

/**
 * Model representing a dependency capability
 */
data class CapabilityModel(
    val group: String,
    val name: String,
    val version: String
)

/**
 * Model representing a module substitution
 */
data class ModuleModel(
    val groupId: String,
    val artifactId: String,
    val version: String? = null
)

/**
 * Model representing a module dependency
 */
data class ModuleDependencyModel(
    val groupId: String,
    val artifactId: String,
    val version: String? = null,
    val transitive: Boolean = true
)

/**
 * Model representing a bundle dependency
 */
data class BundleModel(
    val groupId: String,
    val artifactId: String,
    val version: String? = null,
    val transitive: Boolean = true
) 