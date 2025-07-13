package com.litvin.dependency.converter.maven.extractor

/**
 * Reference data for Maven extractor tests with wrapper tags
 */
object MavenExtractorTestWrapperTagsReference {
    // Single dependency wrapped in dependencyManagement and dependencies tags
    val wrappedSingleDependency = """
        <dependencyManagement>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-dependencies</artifactId>
                    <version>2.6.3</version>
                    <type>pom</type>
                    <scope>import</scope>
                </dependency>
            </dependencies>
        </dependencyManagement>
    """.trimIndent()

    // Multiple dependencies wrapped in dependencies tag
    val wrappedMultipleDependencies = """
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>5.3.9</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>5.3.9</version>
            </dependency>
        </dependencies>
    """.trimIndent()

    // Expected dependencies to be extracted
    val springBootDependency = """
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.6.3</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    """.trimIndent()

    val springCoreDependency = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()

    val springContextDependency = """
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.9</version>
        </dependency>
    """.trimIndent()
}