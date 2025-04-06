package com.litvin.dependency.gradle.groovy.reference

/**
 * Examples of Gradle Groovy artifact-only dependency formats
 */
object GradleGroovyTest10ArtifactOnly {
    // Single file dependency
    val singleFile = """
        implementation files('libs/custom.jar')
    """.trimIndent()
    
    // File tree dependency
    val fileTree = """
        implementation fileTree('libs') {
            include '*.jar'
        }
    """.trimIndent()
    
    // File tree with multiple patterns
    val fileTreeMultiplePatterns = """
        implementation fileTree('libs') {
            include '*.jar'
            exclude 'test-*.jar'
        }
    """.trimIndent()
    
    // Custom artifact
    val customArtifact = """
        implementation('org.example:lib:1.0') {
            artifact {
                name = 'lib'
                type = 'zip'
                extension = 'zip'
                classifier = 'dist'
            }
        }
    """.trimIndent()
    
    // Multiple artifacts
    val multipleArtifacts = """
        implementation('org.example:lib:1.0') {
            artifact {
                name = 'lib'
                type = 'jar'
                classifier = 'sources'
            }
            artifact {
                name = 'lib'
                type = 'jar'
                classifier = 'javadoc'
            }
        }
    """.trimIndent()
    
    // Directory dependency
    val directory = """
        implementation files('build/libs')
    """.trimIndent()
    
    // URL dependency
    val url = """
        implementation files('https://example.com/lib.jar')
    """.trimIndent()
    
    // Artifact with configuration
    val artifactWithConfiguration = """
        implementation('org.example:lib:1.0') {
            artifact {
                name = 'lib'
                type = 'jar'
                classifier = 'sources'
            }
            exclude group: 'commons-logging'
        }
    """.trimIndent()
    
    // Complex artifact combination
    val complexArtifact = """
        implementation('org.example:lib:1.0') {
            artifact {
                name = 'lib'
                type = 'zip'
                extension = 'zip'
                classifier = 'dist'
                url = 'https://example.com/lib-dist.zip'
            }
            capabilities {
                requireCapability('org.example:feature')
            }
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, 'java-runtime')
            }
        }
    """.trimIndent()
} 