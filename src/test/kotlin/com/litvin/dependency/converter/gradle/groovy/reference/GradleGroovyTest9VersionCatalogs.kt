package com.litvin.dependency.converter.gradle.groovy.reference

/**
 * Examples of Gradle Groovy version catalog formats
 */
object GradleGroovyTest9VersionCatalogs {
    // Basic version catalog reference
    val basicCatalogReference = """
        implementation libs.spring.core
    """.trimIndent()
    
    // Version catalog with configuration
    val catalogWithConfiguration = """
        implementation(libs.spring.core) {
            exclude group: 'commons-logging'
        }
    """.trimIndent()
    
    // Version catalog bundle
    val catalogBundle = """
        implementation libs.bundles.spring
    """.trimIndent()
    
    // Version catalog with version
    val catalogWithVersion = """
        implementation(libs.spring.core) {
            version {
                strictly '[1.0,2.0]'
            }
        }
    """.trimIndent()
    
    // Version catalog with capabilities
    val catalogWithCapabilities = """
        implementation(libs.spring.core) {
            capabilities {
                requireCapability('org.springframework:spring-core-capability')
            }
        }
    """.trimIndent()
    
    // Version catalog with attributes
    val catalogWithAttributes = """
        implementation(libs.spring.core) {
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
            }
        }
    """.trimIndent()
    
    // Multiple catalog references
    val multipleCatalogReferences = """
        implementation libs.spring.core
        implementation libs.spring.context
        testImplementation libs.junit.jupiter
    """.trimIndent()
    
    // Catalog with rich version
    val catalogWithRichVersion = """
        implementation(libs.spring.core) {
            version {
                require '[1.0,2.0]'
                prefer '1.5'
                reject '1.4.1'
            }
        }
    """.trimIndent()
    
    // Complex catalog usage
    val complexCatalogUsage = """
        implementation(libs.spring.core) {
            version {
                strictly '[1.0,2.0]'
            }
            capabilities {
                requireCapability('org.springframework:spring-core-capability')
            }
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
            }
            exclude group: 'commons-logging'
        }
    """.trimIndent()
    
    // Version catalog declaration
    val catalogDeclaration = """
        dependencyResolutionManagement {
            versionCatalogs {
                libs {
                    version('spring') {
                        strictly '[5.3.0,6.0.0)'
                        prefer '5.3.9'
                    }
                    library('spring-core', 'org.springframework', 'spring-core').versionRef('spring')
                    library('spring-context', 'org.springframework', 'spring-context').versionRef('spring')
                    bundle('spring', ['spring-core', 'spring-context'])
                }
            }
        }
    """.trimIndent()
} 