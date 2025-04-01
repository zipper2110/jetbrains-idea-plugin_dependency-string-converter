package com.litvin.dependency.maven.reference

/**
 * Maven Dependency Dimension 1: Coordinates
 *
 * This file contains examples of Maven coordinate variations in pom.xml format.
 */

object MavenParserTest1Coordinates {
    // Basic coordinates (most common)
    val springCoreXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()
    
    // Organization/vendor naming patterns
    val guavaXml = """
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>31.1-jre</version>
        </dependency>
    """.trimIndent()
    
    // Reverse domain name pattern
    val commonsLangXml = """
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.12.0</version>
        </dependency>
    """.trimIndent()
    
    // Internal/company artifact pattern
    val companyModuleXml = """
        <dependency>
            <groupId>com.mycompany.project</groupId>
            <artifactId>module-name</artifactId>
            <version>1.0.0</version>
        </dependency>
    """.trimIndent()
    
    // With subgroups/hierarchies
    val springBootStarterXml = """
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <version>2.6.3</version>
        </dependency>
    """.trimIndent()
    
    val quarkusCoreXml = """
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-core</artifactId>
            <version>2.7.5.Final</version>
        </dependency>
    """.trimIndent()
} 