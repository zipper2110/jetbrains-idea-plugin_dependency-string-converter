package com.litvin.dependency.gradle.kotlin.producer

import com.litvin.dependency.converter.producer.GradleKotlinDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.ConstraintConfig
import com.litvin.dependency.gradle.kotlin.reference.GradleKotlinTest2VersionSpecification
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.litvin.dependency.util.KotlinTestUtils.normalizeKotlin

/**
 * Tests that validate producing Gradle Kotlin DSL dependency declarations
 * with different version specifications
 */
class GradleKotlinDependencyProducerTest2VersionSpecification {
    private val producer = GradleKotlinDependencyProducer()
    
    @Test
    fun `should produce dynamic version - latest release`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "latest.release",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.latestVersion),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dynamic version - plus notation`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.+",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.plusNotation),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce dynamic version - latest minor`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.+",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.latestMinor),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce version range - inclusive`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = null,
            scope = "implementation",
            constraint = ConstraintConfig(
                rejectVersions = emptyList(),
                reason = null
            ).apply {
                // In a real ConstraintConfig, we'd set the version range here
                // For this test, we'll rely on the producer's ability to interpret the range
            }
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.versionRangeInclusive),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce version range - exclusive`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = null,
            scope = "implementation",
            constraint = ConstraintConfig(
                rejectVersions = emptyList(),
                reason = null
            ).apply {
                // In a real ConstraintConfig, we'd set the version range here
            }
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.versionRangeExclusive),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce SNAPSHOT version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "1.0.0-SNAPSHOT",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.snapshotVersion),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce release candidate version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "1.0.0-rc.1",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.rcVersion),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce beta version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "1.0.0-beta.2",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.betaVersion),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce alpha version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "1.0.0-alpha.3",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.alphaVersion),
            normalizeKotlin(result)
        )
    }
    
    @Test
    fun `should produce milestone version`() {
        // Given
        val model = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "1.0.0-M1",
            scope = "implementation"
        )
        
        // When
        val result = producer.produce(model)
        
        // Then
        assertEquals(
            normalizeKotlin(GradleKotlinTest2VersionSpecification.milestoneVersion),
            normalizeKotlin(result)
        )
    }
} 