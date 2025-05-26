package com.litvin.dependency.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class DependencyFormatTest {
    
    @Test
    fun `should detect Maven format correctly`() {
        // Given
        val mavenDependency = """
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>5.3.9</version>
            </dependency>
        """.trimIndent()
        
        // When
        val format = DependencyFormat.fromContent(mavenDependency)
        
        // Then
        assertEquals(DependencyFormat.MAVEN, format)
    }
    
    @Test
    fun `should detect Gradle Groovy format correctly`() {
        // Given
        val gradleGroovyDependency = "implementation 'org.springframework:spring-core:5.3.9'"
        
        // When
        val format = DependencyFormat.fromContent(gradleGroovyDependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect Gradle Kotlin format correctly`() {
        // Given
        val gradleKotlinDependency = "implementation(\"org.springframework:spring-core:5.3.9\")"
        
        // When
        val format = DependencyFormat.fromContent(gradleKotlinDependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_KOTLIN, format)
    }
    
    @Test
    fun `should detect Gradle Groovy map notation correctly`() {
        // Given
        val mapNotation = "implementation group: 'org.springframework', name: 'spring-core', version: '5.3.9'"
        
        // When
        val format = DependencyFormat.fromContent(mapNotation)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect other Gradle Groovy configurations correctly`() {
        // Given
        val apiDependency = "api 'com.google.guava:guava:31.1-jre'"
        val compileOnlyDependency = "compileOnly 'org.projectlombok:lombok:1.18.22'"
        val runtimeOnlyDependency = "runtimeOnly 'org.springframework:spring-aspects:5.3.9'"
        val testImplementationDependency = "testImplementation 'org.junit.jupiter:junit-jupiter:5.8.2'"
        
        // When
        val apiFormat = DependencyFormat.fromContent(apiDependency)
        val compileOnlyFormat = DependencyFormat.fromContent(compileOnlyDependency)
        val runtimeOnlyFormat = DependencyFormat.fromContent(runtimeOnlyDependency)
        val testImplementationFormat = DependencyFormat.fromContent(testImplementationDependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, apiFormat)
        assertEquals(DependencyFormat.GRADLE_GROOVY, compileOnlyFormat)
        assertEquals(DependencyFormat.GRADLE_GROOVY, runtimeOnlyFormat)
        assertEquals(DependencyFormat.GRADLE_GROOVY, testImplementationFormat)
    }
    
    @Test
    fun `should detect Gradle Groovy version catalog formats correctly`() {
        // Given
        val versionCatalog = "implementation libs.spring.core"
        val versionCatalogBundle = "implementation libs.bundles.spring"
        
        // When
        val catalogFormat = DependencyFormat.fromContent(versionCatalog)
        val bundleFormat = DependencyFormat.fromContent(versionCatalogBundle)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, catalogFormat)
        assertEquals(DependencyFormat.GRADLE_GROOVY, bundleFormat)
    }
    
    @Test
    fun `should detect Gradle Groovy project dependencies correctly`() {
        // Given
        val projectDependency = "implementation project(':other-module')"
        val projectWithConfig = "implementation project(path: ':other-module', configuration: 'default')"
        
        // When
        val projectFormat = DependencyFormat.fromContent(projectDependency)
        val projectConfigFormat = DependencyFormat.fromContent(projectWithConfig)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, projectFormat)
        assertEquals(DependencyFormat.GRADLE_GROOVY, projectConfigFormat)
    }
    
    @Test
    fun `should detect Gradle Kotlin configurations correctly`() {
        // Given
        val apiDependency = "api(\"com.google.guava:guava:31.1-jre\")"
        val compileOnlyDependency = "compileOnly(\"org.projectlombok:lombok:1.18.22\")"
        
        // When
        val apiFormat = DependencyFormat.fromContent(apiDependency)
        val compileOnlyFormat = DependencyFormat.fromContent(compileOnlyDependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_KOTLIN, apiFormat)
        assertEquals(DependencyFormat.GRADLE_KOTLIN, compileOnlyFormat)
    }
    
    @Test
    fun `should return null for unrecognized format`() {
        // Given
        val unrecognizedDependency = "This is not a valid dependency format"
        
        // When
        val format = DependencyFormat.fromContent(unrecognizedDependency)
        
        // Then
        assertNull(format)
    }
} 