package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DependencyTextConverterTest {
    private val converter = DependencyTextConverter()

    @Test
    fun `should convert Maven property to Gradle variable`() {
        val mavenDependency = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${'$'}{spring-boot.version}</version>
            </dependency>
        """.trimIndent()

        val expectedGradle = "implementation 'org.springframework.boot:spring-boot-starter-web:\${springBootVersion}'"
        val actualGradle = converter.convertMavenToGradle(mavenDependency, false)
        assertEquals(expectedGradle, actualGradle)
    }

    @Test
    fun `should convert Gradle variable to Maven property`() {
        val gradleDependency = "implementation 'org.springframework.boot:spring-boot-starter-web:\${springBootVersion}'"
        
        val expectedMaven = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${'$'}{spring-boot.version}</version>
            </dependency>
        """.trimIndent()
        
        val actualMaven = converter.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMaven, actualMaven)
    }

    @Test
    fun `should handle multiple variables in same dependency`() {
        val mavenDependency = """
            <dependency>
                <groupId>${'$'}{project.groupId}</groupId>
                <artifactId>${'$'}{project.artifactId}</artifactId>
                <version>${'$'}{project.version}</version>
            </dependency>
        """.trimIndent()

        val expectedGradle = "implementation '\${project.groupId}:\${project.artifactId}:\${project.version}'"
        val actualGradle = converter.convertMavenToGradle(mavenDependency, false)
        assertEquals(expectedGradle, actualGradle)
    }

    @Test
    fun `should handle nested properties`() {
        val mavenDependency = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${'$'}{spring-boot.${'$'}{env}.version}</version>
            </dependency>
        """.trimIndent()

        val expectedGradle = "implementation 'org.springframework.boot:spring-boot-starter-web:\${springBoot}.\${env}.version'"
        val actualGradle = converter.convertMavenToGradle(mavenDependency, false)
        assertEquals(expectedGradle, actualGradle)
    }

    @Test
    fun `should handle Kotlin DSL variables`() {
        val gradleDependency = "implementation(\"org.springframework.boot:spring-boot-starter-web:\${springBootVersion}\")"
        
        val expectedMaven = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${'$'}{spring-boot.version}</version>
            </dependency>
        """.trimIndent()
        
        val actualMaven = converter.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMaven, actualMaven)
    }

    @Test
    fun `should preserve non-variable content`() {
        val mavenDependency = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>2.7.0</version>
                <scope>compile</scope>
            </dependency>
        """.trimIndent()

        val expectedGradle = "implementation 'org.springframework.boot:spring-boot-starter-web:2.7.0'"
        val actualGradle = converter.convertMavenToGradle(mavenDependency, false)
        assertEquals(expectedGradle, actualGradle)
    }

    @Test
    fun `test nested variable conversion`() {
        // Test Maven to Gradle conversion with nested variables
        val mavenDependency = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${'$'}{spring-boot.${'$'}{env}.version}</version>
            </dependency>
        """.trimIndent()

        val expectedGradle = """implementation("org.springframework.boot:spring-boot-starter-web:${'$'}{springBoot}.${'$'}{env}.version")"""
        val actualGradle = converter.convertMavenToGradle(mavenDependency, true)
        assertEquals(expectedGradle, actualGradle)

        // Test Gradle to Maven conversion with nested variables
        val gradleDependency = """implementation("org.springframework.boot:spring-boot-starter-web:${'$'}{springBoot}.${'$'}{env}.version")"""
        val expectedMaven = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${'$'}{spring-boot.${'$'}{env}.version}</version>
            </dependency>
        """.trimIndent()
        val actualMaven = converter.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMaven, actualMaven)
    }

    @Test
    fun `test simple variable conversion`() {
        // Test Maven to Gradle conversion with simple variable
        val mavenDependency = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${'$'}{spring-boot.version}</version>
            </dependency>
        """.trimIndent()

        val expectedGradle = """implementation("org.springframework.boot:spring-boot-starter-web:${'$'}{springBootVersion}")"""
        val actualGradle = converter.convertMavenToGradle(mavenDependency, true)
        assertEquals(expectedGradle, actualGradle)

        // Test Gradle to Maven conversion with simple variable
        val gradleDependency = """implementation("org.springframework.boot:spring-boot-starter-web:${'$'}{springBootVersion}")"""
        val expectedMaven = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${'$'}{spring-boot.version}</version>
            </dependency>
        """.trimIndent()
        val actualMaven = converter.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMaven, actualMaven)
    }
} 