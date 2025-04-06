package com.litvin.dependency.gradle.groovy.reference

/**
 * Examples of Gradle Groovy capability formats
 */
object GradleGroovyTest7Capabilities {
    // Basic capability requirement
    val basicCapability = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:special-feature')
            }
        }
    """.trimIndent()
    
    // Multiple capabilities
    val multipleCapabilities = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:feature1:1.0')
                requireCapability('org.example:feature2:1.0')
            }
        }
    """.trimIndent()
    
    // Capability with version
    val capabilityWithVersion = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:feature:2.0')
            }
        }
    """.trimIndent()
    
    // Capability with reason
    val capabilityWithReason = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:feature') {
                    because 'needed for special functionality'
                }
            }
        }
    """.trimIndent()
    
    // Capability with attributes
    val capabilityWithAttributes = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:feature') {
                    attributes {
                        attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
                    }
                }
            }
        }
    """.trimIndent()
    
    // Capability with version constraints
    val capabilityWithVersionConstraints = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:feature') {
                    version {
                        require '[1.0,2.0)'
                        prefer '1.5'
                    }
                }
            }
        }
    """.trimIndent()
    
    // Optional capability
    val optionalCapability = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:feature') {
                    optional = true
                }
            }
        }
    """.trimIndent()
    
    // Capability with configuration
    val capabilityWithConfiguration = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:feature') {
                    configuration = 'runtime'
                }
            }
        }
    """.trimIndent()
    
    // Complex capability combination
    val complexCapabilityCombination = """
        implementation('org.example:lib:1.0') {
            capabilities {
                requireCapability('org.example:feature1:1.0') {
                    because 'needed for feature 1'
                    attributes {
                        attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
                    }
                }
                requireCapability('org.example:feature2:2.0') {
                    version {
                        require '[2.0,3.0)'
                    }
                    optional = true
                }
            }
        }
    """.trimIndent()
} 