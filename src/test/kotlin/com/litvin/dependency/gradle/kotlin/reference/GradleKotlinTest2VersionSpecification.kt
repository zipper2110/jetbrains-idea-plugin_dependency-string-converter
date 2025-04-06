package com.litvin.dependency.gradle.kotlin.reference

/**
 * Examples of Gradle Kotlin DSL version specification formats
 */
object GradleKotlinTest2VersionSpecification {
    // Dynamic version - latest
    val latestVersion = """
        implementation("org.springframework:spring-core:latest.release")
    """.trimIndent()
    
    // Dynamic version - plus notation
    val plusNotation = """
        implementation("org.springframework:spring-core:5.+")
    """.trimIndent()
    
    // Dynamic version - latest minor
    val latestMinor = """
        implementation("org.springframework:spring-core:5.3.+")
    """.trimIndent()
    
    // Version range - inclusive
    val versionRangeInclusive = """
        implementation("org.springframework:spring-core") {
            version {
                strictly("[1.0,2.0]")
            }
        }
    """.trimIndent()
    
    // Version range - exclusive
    val versionRangeExclusive = """
        implementation("org.springframework:spring-core") {
            version {
                strictly("(1.0,2.0)")
            }
        }
    """.trimIndent()
    
    // Version range - mixed
    val versionRangeMixed = """
        implementation("org.springframework:spring-core") {
            version {
                strictly("[1.0,2.0)")
            }
        }
    """.trimIndent()
    
    // Rich version - prefer
    val richVersionPrefer = """
        implementation("org.springframework:spring-core") {
            version {
                prefer("1.5")
            }
        }
    """.trimIndent()
    
    // Rich version - reject
    val richVersionReject = """
        implementation("org.springframework:spring-core") {
            version {
                reject("1.4.1")
            }
        }
    """.trimIndent()
    
    // Rich version - require
    val richVersionRequire = """
        implementation("org.springframework:spring-core") {
            version {
                require("1.5")
            }
        }
    """.trimIndent()
    
    // Rich version - strictly
    val richVersionStrictly = """
        implementation("org.springframework:spring-core") {
            version {
                strictly("1.5")
            }
        }
    """.trimIndent()
    
    // Rich version - combination
    val richVersionCombination = """
        implementation("org.springframework:spring-core") {
            version {
                require("[1.0,2.0]")
                prefer("1.5")
                reject("1.4.1")
            }
        }
    """.trimIndent()
    
    // SNAPSHOT version
    val snapshotVersion = """
        implementation("org.springframework:spring-core:1.0.0-SNAPSHOT")
    """.trimIndent()
    
    // Release candidate version
    val rcVersion = """
        implementation("org.springframework:spring-core:1.0.0-rc.1")
    """.trimIndent()
    
    // Beta version
    val betaVersion = """
        implementation("org.springframework:spring-core:1.0.0-beta.2")
    """.trimIndent()
    
    // Alpha version
    val alphaVersion = """
        implementation("org.springframework:spring-core:1.0.0-alpha.3")
    """.trimIndent()
    
    // Milestone version
    val milestoneVersion = """
        implementation("org.springframework:spring-core:1.0.0-M1")
    """.trimIndent()
} 