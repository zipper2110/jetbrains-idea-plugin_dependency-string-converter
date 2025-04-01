package com.litvin.dependency.maven.reference

/**
 * Maven Dependency Dimension 2: Version Specification
 *
 * This file contains examples of different version specification patterns in Maven dependencies.
 */

object MavenDimension2VersionSpecification {
    // Exact version (most common)
    val exactVersionXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()
    
    // SNAPSHOT version
    val snapshotVersionXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
    """.trimIndent()
    
    // Version with qualifier
    val qualifierVersionXml = """
        <dependency>
            <groupId>org.apache.tomcat</groupId>
            <artifactId>tomcat-catalina</artifactId>
            <version>9.0.56-beta</version>
        </dependency>
    """.trimIndent()
    
    // Version with timestamp (often for SNAPSHOT resolution)
    val timestampVersionXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>1.0.0-20220131.131415-42</version>
        </dependency>
    """.trimIndent()
    
    // Range: Inclusive lower bound, exclusive upper bound
    val versionRangeInclusiveExclusiveXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>[1.0.0,2.0.0)</version>
        </dependency>
    """.trimIndent()
    
    // Range: Inclusive lower bound, inclusive upper bound
    val versionRangeInclusiveInclusiveXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>[1.0.0,2.0.0]</version>
        </dependency>
    """.trimIndent()
    
    // Range: Exclusive lower bound, inclusive upper bound
    val versionRangeExclusiveInclusiveXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>(1.0.0,2.0.0]</version>
        </dependency>
    """.trimIndent()
    
    // Range: Any version greater than or equal to
    val versionRangeGreaterThanOrEqualXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>[1.0.0,)</version>
        </dependency>
    """.trimIndent()
    
    // Range: Any version less than or equal to
    val versionRangeLessThanOrEqualXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>(,2.0.0]</version>
        </dependency>
    """.trimIndent()
    
    // LATEST (not recommended, but used)
    val latestVersionXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>LATEST</version>
        </dependency>
    """.trimIndent()
    
    // RELEASE (not recommended, but used)
    val releaseVersionXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>RELEASE</version>
        </dependency>
    """.trimIndent()
} 