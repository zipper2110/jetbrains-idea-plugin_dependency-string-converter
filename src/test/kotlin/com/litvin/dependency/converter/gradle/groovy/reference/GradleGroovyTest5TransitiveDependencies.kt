package com.litvin.dependency.converter.gradle.groovy.reference

/**
 * Examples of Gradle Groovy transitive dependency formats
 */
object GradleGroovyTest5TransitiveDependencies {
    // Single exclusion
    val singleExclusion = """
        implementation('org.springframework:spring-core:5.3.9') {
            exclude group: 'commons-logging', module: 'commons-logging'
        }
    """.trimIndent()
    
    // Multiple exclusions
    val multipleExclusions = """
        implementation('org.springframework:spring-core:5.3.9') {
            exclude group: 'commons-logging', module: 'commons-logging'
            exclude group: 'log4j', module: 'log4j'
        }
    """.trimIndent()
    
    // Group-only exclusion
    val groupOnlyExclusion = """
        implementation('org.springframework:spring-core:5.3.9') {
            exclude group: 'commons-logging'
        }
    """.trimIndent()
    
    // Module-only exclusion
    val moduleOnlyExclusion = """
        implementation('org.springframework:spring-core:5.3.9') {
            exclude module: 'commons-logging'
        }
    """.trimIndent()
    
    // Transitive false
    val transitiveDisabled = """
        implementation('org.springframework:spring-core:5.3.9') {
            transitive = false
        }
    """.trimIndent()
    
    // Capability conflict resolution
    val capabilityConflict = """
        implementation('org.springframework:spring-core:5.3.9') {
            capabilities {
                requireCapability('org.springframework:spring-core-capability')
            }
        }
    """.trimIndent()
    
    // Dependency substitution
    val dependencySubstitution = """
        implementation('org.springframework:spring-core:5.3.9') {
            substitute module('org.old:module') with module('org.new:module:1.0')
        }
    """.trimIndent()
    
    // Force version
    val forceVersion = """
        implementation('org.springframework:spring-core:5.3.9') {
            force = true
        }
    """.trimIndent()
    
    // Exclude all transitive dependencies
    val excludeAllTransitive = """
        implementation('org.springframework:spring-core:5.3.9') {
            exclude group: '*', module: '*'
        }
    """.trimIndent()
    
    // Complex combination
    val complexCombination = """
        implementation('org.springframework:spring-core:5.3.9') {
            exclude group: 'commons-logging', module: 'commons-logging'
            exclude group: 'log4j'
            capabilities {
                requireCapability('org.springframework:spring-core-capability')
            }
            force = true
        }
    """.trimIndent()
    
    // Configuration-wide exclusions
    val configurationExclusions = """
        configurations {
            implementation {
                exclude group: 'commons-logging', module: 'commons-logging'
            }
        }
        
        implementation 'org.springframework:spring-core:5.3.9'
    """.trimIndent()
    
    // Dependency constraints
    val dependencyConstraints = """
        dependencies {
            constraints {
                implementation('org.springframework:spring-core:5.3.9') {
                    because 'tested with this version'
                }
            }
        }
    """.trimIndent()
} 