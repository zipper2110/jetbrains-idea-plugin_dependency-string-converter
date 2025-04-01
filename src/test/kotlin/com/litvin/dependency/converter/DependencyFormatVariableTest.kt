package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DependencyFormatVariableTest {
    private val variableConverter = DependencyTextConverter()

    @Test
    fun `convert Maven with property to Gradle Kotlin`() {
        // Given
        val maven = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter</artifactId>
                <version>${"$"}{spring-boot.version}</version>
            </dependency>
        """.trimIndent()
        
        // When
        val kotlin = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_KOTLIN)
        
        // Then
        assertEquals("""implementation("org.springframework.boot:spring-boot-starter:${"$"}{springBootVersion}")""", kotlin)
    }
    
    @Test
    fun `convert Maven with property to Gradle Groovy`() {
        // Given
        val maven = """
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter</artifactId>
                <version>${"$"}{spring-boot.version}</version>
            </dependency>
        """.trimIndent()
        
        // When
        val groovy = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_GROOVY)
        
        // Then
        assertEquals("""implementation 'org.springframework.boot:spring-boot-starter:${"$"}{springBootVersion}'""", groovy)
    }
    
    @Test
    fun `convert Gradle Kotlin with variable to Maven`() {
        // Given
        val kotlin = """implementation("org.springframework.boot:spring-boot-starter:${"$"}{springBootVersion}")"""
        
        // When
        val maven = variableConverter.convertGradleToMaven(kotlin, DependencyFormat.GRADLE_KOTLIN)
        
        // Then
        assertTrue(maven.contains("<groupId>org.springframework.boot</groupId>"))
        assertTrue(maven.contains("<artifactId>spring-boot-starter</artifactId>"))
        assertTrue(maven.contains("<version>${"$"}{spring-boot.version}</version>"))
    }
    
    @Test
    fun `convert Gradle Groovy with variable to Maven`() {
        // Given
        val groovy = """implementation 'org.springframework.boot:spring-boot-starter:${"$"}{springBootVersion}'"""
        
        // When
        val maven = variableConverter.convertGradleToMaven(groovy, DependencyFormat.GRADLE_GROOVY)
        
        // Then
        assertTrue(maven.contains("<groupId>org.springframework.boot</groupId>"))
        assertTrue(maven.contains("<artifactId>spring-boot-starter</artifactId>"))
        assertTrue(maven.contains("<version>${"$"}{spring-boot.version}</version>"))
    }
    
    @Test
    fun `convert Maven with nested property to Gradle Kotlin`() {
        // Given
        val maven = """
            <dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>${"$"}{project.version}</version>
            </dependency>
        """.trimIndent()
        
        // When
        val kotlin = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_KOTLIN)
        
        // Then
        assertEquals("""implementation("org.example:lib:${"$"}{project.version}")""", kotlin)
    }
    
    @Test
    fun `convert Gradle Kotlin with project property to Maven`() {
        // Given
        val kotlin = """implementation("org.example:lib:${"$"}{project.version}")"""
        
        // When
        val maven = variableConverter.convertGradleToMaven(kotlin, DependencyFormat.GRADLE_KOTLIN)
        
        // Then
        assertTrue(maven.contains("<version>${"$"}{project.version}</version>"))
    }
    
    @Test
    fun `convert between formats with complex properties`() {
        // Given - a Maven dependency with a complex property structure
        val maven = """
            <dependency>
                <groupId>org.example</groupId>
                <artifactId>complex-lib</artifactId>
                <version>${"$"}{major.version}.${"$"}{minor.version}</version>
            </dependency>
        """.trimIndent()
        
        // When - convert to Gradle Kotlin and back
        val kotlin = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_KOTLIN)
        val mavenAgain = variableConverter.convertGradleToMaven(kotlin, DependencyFormat.GRADLE_KOTLIN)
        
        // Then - the original properties should be preserved through the round trip
        assertTrue(kotlin.contains("${"$"}{major.version}.${"$"}{minor.version}"))
        assertTrue(mavenAgain.contains("<version>${"$"}{major.version}.${"$"}{minor.version}</version>"))
    }
} 