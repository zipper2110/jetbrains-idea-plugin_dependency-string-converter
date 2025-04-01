package com.litvin.dependency.maven.reference

/**
 * Maven Dependency Dimension 6: Properties/Variables
 *
 * This file contains examples of Maven dependencies using property variables.
 */

object MavenDimension6PropertiesVariables {
    // Using property for version
    val versionPropertyXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${'$'}{spring.version}</version>
        </dependency>
    """.trimIndent()
    
    // Using property for groupId
    val groupIdPropertyXml = """
        <dependency>
            <groupId>${'$'}{springframework.groupId}</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()
    
    // Using property for artifactId
    val artifactIdPropertyXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>${'$'}{spring.core.artifactId}</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()
    
    // Using multiple properties
    val multiplePropertiesXml = """
        <dependency>
            <groupId>${'$'}{springframework.groupId}</groupId>
            <artifactId>${'$'}{spring.core.artifactId}</artifactId>
            <version>${'$'}{spring.version}</version>
        </dependency>
    """.trimIndent()
    
    // Using built-in Maven property
    val builtInPropertyXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>${'$'}{project.version}</version>
        </dependency>
    """.trimIndent()
    
    // Using property for scope
    val scopePropertyXml = """
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>${'$'}{junit.scope}</scope>
        </dependency>
    """.trimIndent()
    
    // Using property with classifier
    val classifierPropertyXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <classifier>${'$'}{docs.classifier}</classifier>
        </dependency>
    """.trimIndent()
    
    // Using property for systemPath
    val systemPathPropertyXml = """
        <dependency>
            <groupId>com.oracle</groupId>
            <artifactId>ojdbc8</artifactId>
            <version>19.3.0</version>
            <scope>system</scope>
            <systemPath>${'$'}{oracle.jdbc.path}</systemPath>
        </dependency>
    """.trimIndent()
    
    // Using property in version range
    val versionRangePropertyXml = """
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>my-library</artifactId>
            <version>[${'$'}{min.version},${'$'}{max.version})</version>
        </dependency>
    """.trimIndent()
} 