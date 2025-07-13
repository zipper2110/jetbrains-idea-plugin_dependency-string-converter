package com.litvin.dependency.converter.maven.reference

/**
 * Maven Dependency Dimension 4: Scope
 *
 * This file contains examples of different dependency scopes in Maven dependencies.
 */

object MavenParserTest4Scope {
    // Default scope (compile) - typically omitted as it's the default
    val defaultScopeXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()

    // Explicit compile scope
    val compileScopeXml = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
            <scope>compile</scope>
        </dependency>
    """.trimIndent()

    // Runtime scope
    val runtimeScopeXml = """
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.3.1</version>
            <scope>runtime</scope>
        </dependency>
    """.trimIndent()

    // Test scope
    val testScopeXml = """
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    """.trimIndent()

    // Provided scope
    val providedScopeXml = """
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
    """.trimIndent()

    // System scope (requires systemPath)
    val systemScopeXml = """
        <dependency>
            <groupId>com.oracle</groupId>
            <artifactId>ojdbc8</artifactId>
            <version>19.3.0</version>
            <scope>system</scope>
            <systemPath>${'$'}{basedir}/lib/ojdbc8.jar</systemPath>
        </dependency>
    """.trimIndent()

    // Import scope (only for pom type in dependencyManagement)
    val importScopeXml = """
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-dependencies</artifactId>
                    <version>2.6.3</version>
                    <type>pom</type>
                    <scope>import</scope>
                </dependency>
    """.trimIndent()
}
