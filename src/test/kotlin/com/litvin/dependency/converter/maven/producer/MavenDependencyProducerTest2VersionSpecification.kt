package com.litvin.dependency.converter.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.converter.maven.reference.MavenParserTest2VersionSpecification
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.litvin.dependency.util.XmlTestUtils.normalizeXml

/**
 * Tests that validate producing of Maven coordinates with various version specifications 
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest2VersionSpecification {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun `should produce dependency with exact version`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.exactVersionXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with snapshot version`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-SNAPSHOT"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.snapshotVersionXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with qualifier version`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.apache.tomcat",
            artifactId = "tomcat-catalina",
            version = "9.0.56-beta"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.qualifierVersionXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with timestamp version`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-20220131.131415-42"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.timestampVersionXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with version range inclusive exclusive`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,2.0.0)"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeInclusiveExclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with version range inclusive inclusive`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,2.0.0]"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeInclusiveInclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with version range exclusive inclusive`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "(1.0.0,2.0.0]"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeExclusiveInclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with version range greater than or equal`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,)"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeGreaterThanOrEqualXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with version range less than or equal`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "(,2.0.0]"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeLessThanOrEqualXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with latest version`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "LATEST"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.latestVersionXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with release version`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "RELEASE"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.releaseVersionXml), normalizeXml(result))
    }
} 