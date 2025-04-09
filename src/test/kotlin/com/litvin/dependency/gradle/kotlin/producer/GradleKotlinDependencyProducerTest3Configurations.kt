package com.litvin.dependency.gradle.kotlin.producer

import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.gradle.kotlin.reference.GradleKotlinTest3Configurations
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.litvin.dependency.util.KotlinTestUtils.normalizeKotlin

/**
 * Tests that validate producing Gradle Kotlin DSL dependency declarations
 * with different configuration types
 */
class GradleKotlinDependencyProducerTest3Configurations {
    private val producer = GradleKotlinDependencyProducer()
    
    @Test
    fun `should produce implementation configuration`() {
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
            normalizeKotlin(GradleKotlinTest3Configurations.implementation),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce api configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "api"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.api),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce compileOnly configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "compileOnly"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.compileOnly),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce runtimeOnly configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "runtimeOnly"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.runtimeOnly),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce testImplementation configuration`() {
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
            normalizeKotlin(GradleKotlinTest3Configurations.testImplementation),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce testCompileOnly configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.projectlombok",
            artifactId = "lombok",
            version = "1.18.22",
            scope = "testCompileOnly"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.testCompileOnly),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce testRuntimeOnly configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.junit.jupiter",
            artifactId = "junit-jupiter-engine",
            version = "5.8.2",
            scope = "testRuntimeOnly"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.testRuntimeOnly),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce annotationProcessor configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.projectlombok",
            artifactId = "lombok",
            version = "1.18.22",
            scope = "annotationProcessor"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.annotationProcessor),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce kapt configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.mapstruct",
            artifactId = "mapstruct-processor",
            version = "1.4.2.Final",
            scope = "kapt"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.kapt),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce debug implementation configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "com.squareup.leakcanary",
            artifactId = "leakcanary-android",
            version = "2.8.1",
            scope = "debugImplementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.debugImplementation),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce release implementation configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "com.example",
            artifactId = "release-only-lib",
            version = "1.0.0",
            scope = "releaseImplementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.releaseImplementation),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce custom configuration`() {
        // Given
        val model = DependencyModel(
            groupId = "org.example",
            artifactId = "custom-lib",
            version = "1.0.0",
            scope = "myCustomConfiguration"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest3Configurations.customConfiguration),
            normalizeKotlin(result)
        )
    }
} 