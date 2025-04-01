package com.litvin.dependency.maven.reference

/**
 * Maven Dependency Dimension 3: Type/Packaging
 *
 * This file contains examples of different packaging type specifications in Maven dependencies.
 */

object MavenParserTest3TypePackaging {
    // Default type (jar) - typically omitted as it's the default
    val defaultTypeXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()
    
    // Explicit jar type
    val jarTypeXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <type>jar</type>
        </dependency>
    """.trimIndent()
    
    // WAR packaging
    val warTypeXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>sample-webapp</artifactId>
            <version>1.0.0</version>
            <type>war</type>
        </dependency>
    """.trimIndent()
    
    // EAR packaging
    val earTypeXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>enterprise-app</artifactId>
            <version>1.0.0</version>
            <type>ear</type>
        </dependency>
    """.trimIndent()
    
    // POM packaging (often used for BOMs or parent POMs)
    val pomTypeXml = """
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.6.3</version>
            <type>pom</type>
        </dependency>
    """.trimIndent()
    
    // Maven plugin packaging
    val mavenPluginTypeXml = """
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.10.1</version>
            <type>maven-plugin</type>
        </dependency>
    """.trimIndent()
    
    // Test JAR packaging
    val testJarTypeXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>library-with-test-utils</artifactId>
            <version>1.0.0</version>
            <type>test-jar</type>
        </dependency>
    """.trimIndent()
    
    // Ejb packaging
    val ejbTypeXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>enterprise-bean</artifactId>
            <version>1.0.0</version>
            <type>ejb</type>
        </dependency>
    """.trimIndent()
    
    // Bundle packaging (OSGi)
    val bundleTypeXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>osgi-bundle</artifactId>
            <version>1.0.0</version>
            <type>bundle</type>
        </dependency>
    """.trimIndent()
    
    // Zip file
    val zipTypeXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>resources-package</artifactId>
            <version>1.0.0</version>
            <type>zip</type>
        </dependency>
    """.trimIndent()
} 