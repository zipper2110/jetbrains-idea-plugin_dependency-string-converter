package com.litvin.dependency.converter.gradle.groovy.reference

/**
 * Examples of Gradle Groovy project dependency formats
 */
object GradleGroovyTest8ProjectDependencies {
    // Basic project dependency
    val basicProject = """
        implementation project(':other-module')
    """.trimIndent()
    
    // Project dependency with configuration
    val projectWithConfiguration = """
        implementation project(path: ':other-module', configuration: 'default')
    """.trimIndent()
    
    // Project dependency with capabilities
    val projectWithCapabilities = """
        implementation(project(':other-module')) {
            capabilities {
                requireCapability('org.example:feature')
            }
        }
    """.trimIndent()
    
    // Project dependency with attributes
    val projectWithAttributes = """
        implementation(project(':other-module')) {
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
            }
        }
    """.trimIndent()
    
    // Project dependency with exclusions
    val projectWithExclusions = """
        implementation(project(':other-module')) {
            exclude group: 'commons-logging', module: 'commons-logging'
        }
    """.trimIndent()
    
    // Project dependency with transitive = false
    val projectWithoutTransitive = """
        implementation(project(':other-module')) {
            transitive = false
        }
    """.trimIndent()
    
    // Project dependency with build type
    val projectWithBuildType = """
        implementation project(path: ':other-module', buildType: 'release')
    """.trimIndent()
    
    // Project dependency with configuration and capabilities
    val projectWithConfigAndCapabilities = """
        implementation(project(path: ':other-module', configuration: 'default')) {
            capabilities {
                requireCapability('org.example:feature')
            }
        }
    """.trimIndent()
    
    // Complex project dependency
    val complexProject = """
        implementation(project(':other-module')) {
            capabilities {
                requireCapability('org.example:feature1')
                requireCapability('org.example:feature2')
            }
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
                attribute(Category.CATEGORY_ATTRIBUTE, 'library')
            }
            exclude group: 'commons-logging'
            transitive = false
        }
    """.trimIndent()
    
    // Multiple project dependencies
    val multipleProjects = """
        implementation project(':module1')
        implementation project(':module2')
        testImplementation project(path: ':test-utils', configuration: 'testArtifacts')
    """.trimIndent()
} 