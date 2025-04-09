package com.litvin.dependency.gradle.kotlin.producer

import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.gradle.kotlin.reference.GradleKotlinTest1BasicCoordinates
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.litvin.dependency.util.KotlinTestUtils.normalizeKotlin

/**
 * Tests that validate producing of Gradle Kotlin DSL coordinates
 * using GradleKotlinDependencyProducer
 */
class GradleKotlinDependencyProducerTest1BasicCoordinates {
    private val producer = GradleKotlinDependencyProducer()
    
    @Test
    fun `should produce basic implementation dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest1BasicCoordinates.basicStringNotation),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency without version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = null,
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest1BasicCoordinates.noVersionDependency),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce api dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "com.google.guava",
            artifactId = "guava",
            version = "31.1-jre",
            scope = "api"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest1BasicCoordinates.apiDependency),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce compileOnly dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "org.projectlombok",
            artifactId = "lombok",
            version = "1.18.22",
            scope = "compileOnly"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest1BasicCoordinates.compileOnlyDependency),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce testImplementation dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "org.junit.jupiter",
            artifactId = "junit-jupiter",
            version = "5.8.2",
            scope = "testImplementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin("testImplementation(\"org.junit.jupiter:junit-jupiter:5.8.2\")"),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dependency with special characters`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "lib-with-special-chars",
            version = "1.0.0-SNAPSHOT",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin("implementation(\"com.example:lib-with-special-chars:1.0.0-SNAPSHOT\")"),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce company module dependency`() {
        // Given
        val dependency = DependencyModel(
            groupId = "com.mycompany.project",
            artifactId = "module-name",
            version = "1.0.0",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest1BasicCoordinates.companyModuleDependency),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce Spring Boot starter dependency`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework.boot",
            artifactId = "spring-boot-starter",
            version = "2.6.3",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest1BasicCoordinates.springBootStarterDependency),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce Quarkus dependency`() {
        // Given
        val dependency = DependencyModel(
            groupId = "io.quarkus",
            artifactId = "quarkus-core",
            version = "2.7.5.Final",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest1BasicCoordinates.quarkusDependency),
            normalizeKotlin(result)
        )
    }
} 