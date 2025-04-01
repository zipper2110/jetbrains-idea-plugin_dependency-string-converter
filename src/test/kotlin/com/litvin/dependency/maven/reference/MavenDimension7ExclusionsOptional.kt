package com.litvin.dependency.maven.reference

/**
 * Maven Dependency Dimension 7: Exclusions and Optional Dependencies
 *
 * This file contains examples of Maven dependencies using exclusions and optional flags.
 */

object MavenDimension7ExclusionsOptional {
    // With single exclusion
    val singleExclusionXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    """.trimIndent()
    
    // With multiple exclusions
    val multipleExclusionsXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    """.trimIndent()
    
    // Wildcard exclusion (exclude all artifacts of a group)
    val wildcardExclusionXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    """.trimIndent()
    
    // Optional dependency
    val optionalDependencyXml = """
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.1.210</version>
            <optional>true</optional>
        </dependency>
    """.trimIndent()
    
    // Combination of exclusions and optional
    val exclusionsAndOptionalXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.9</version>
            <optional>true</optional>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    """.trimIndent()
    
    // Exclusion with scope and type
    val exclusionWithScopeAndTypeXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.9</version>
            <scope>test</scope>
            <type>jar</type>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    """.trimIndent()
    
    // Optional with classifier
    val optionalWithClassifierXml = """
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.3.1</version>
            <classifier>jre7</classifier>
            <optional>true</optional>
        </dependency>
    """.trimIndent()
} 