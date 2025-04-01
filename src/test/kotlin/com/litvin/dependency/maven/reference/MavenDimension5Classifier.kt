package com.litvin.dependency.maven.reference

/**
 * Maven Dependency Dimension 5: Classifier
 *
 * This file contains examples of different classifiers in Maven dependencies.
 */

object MavenDimension5Classifier {
    // No classifier (standard JAR)
    val noClassifierXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()
    
    // Sources classifier (for source code JARs)
    val sourcesClassifierXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <classifier>sources</classifier>
        </dependency>
    """.trimIndent()
    
    // Javadoc classifier (for javadoc JARs)
    val javadocClassifierXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <classifier>javadoc</classifier>
        </dependency>
    """.trimIndent()
    
    // Tests classifier (for test code JARs)
    val testsClassifierXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <classifier>tests</classifier>
        </dependency>
    """.trimIndent()
    
    // Platform-specific classifier (e.g., for native libraries)
    val nativeWindowsClassifierXml = """
        <dependency>
            <groupId>org.lwjgl</groupId>
            <artifactId>lwjgl</artifactId>
            <version>3.3.1</version>
            <classifier>natives-windows</classifier>
        </dependency>
    """.trimIndent()
    
    // JDK/Java version specific classifier
    val jdk11ClassifierXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>custom-library</artifactId>
            <version>1.0.0</version>
            <classifier>jdk11</classifier>
        </dependency>
    """.trimIndent()
    
    // Combined with type
    val javadocWithTypeXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <type>jar</type>
            <classifier>javadoc</classifier>
        </dependency>
    """.trimIndent()
    
    // Combined with scope
    val sourcesWithScopeXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <classifier>sources</classifier>
            <scope>provided</scope>
        </dependency>
    """.trimIndent()
} 