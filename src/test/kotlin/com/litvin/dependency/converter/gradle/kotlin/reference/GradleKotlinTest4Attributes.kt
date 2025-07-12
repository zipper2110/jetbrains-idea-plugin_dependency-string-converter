package com.litvin.dependency.converter.gradle.kotlin.reference

/**
 * Examples of Gradle Kotlin DSL dependency attribute formats
 */
object GradleKotlinTest4Attributes {
    // Basic attribute
    val basicAttribute = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 11)
            }
        }
    """.trimIndent()
    
    // Multiple attributes
    val multipleAttributes = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 11)
                attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                attribute(Category.CATEGORY_ATTRIBUTE, "library")
            }
        }
    """.trimIndent()
    
    // Custom attribute
    val customAttribute = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(Attribute.of("my.custom.attribute", String::class.java), "custom-value")
            }
        }
    """.trimIndent()
    
    // Library elements attribute
    val libraryElements = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, "jar")
            }
        }
    """.trimIndent()
    
    // Bundling attribute
    val bundling = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(Bundling.BUNDLING_ATTRIBUTE, "external")
            }
        }
    """.trimIndent()
    
    // Documentation type attribute
    val documentationType = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(DocsType.DOCS_TYPE_ATTRIBUTE, "javadoc")
            }
        }
    """.trimIndent()
    
    // Status attribute
    val status = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(Status.STATUS_ATTRIBUTE, "release")
            }
        }
    """.trimIndent()
    
    // Attributes with artifact type
    val attributesWithArtifactType = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, "jar")
                attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
            }
        }
    """.trimIndent()
    
    // Attributes with classifier
    val attributesWithClassifier = """
        implementation("org.springframework:spring-core:5.3.9:sources") {
            attributes {
                attribute(Category.CATEGORY_ATTRIBUTE, "documentation")
                attribute(DocsType.DOCS_TYPE_ATTRIBUTE, "source")
            }
        }
    """.trimIndent()
    
    // Complex combination
    val complexCombination = """
        implementation("org.springframework:spring-core:5.3.9") {
            attributes {
                attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 11)
                attribute(Usage.USAGE_ATTRIBUTE, "java-runtime")
                attribute(Category.CATEGORY_ATTRIBUTE, "library")
                attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, "jar")
                attribute(Bundling.BUNDLING_ATTRIBUTE, "external")
                attribute(Status.STATUS_ATTRIBUTE, "release")
            }
        }
    """.trimIndent()
} 