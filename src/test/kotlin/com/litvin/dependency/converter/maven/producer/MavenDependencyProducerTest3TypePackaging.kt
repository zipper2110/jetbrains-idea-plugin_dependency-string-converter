package com.litvin.dependency.converter.maven.producer

import com.litvin.dependency.converter.maven.reference.MavenParserTest3TypePackaging
import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.util.XmlTestUtils.normalizeXml
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of Maven coordinates with various type/packaging formats
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest3TypePackaging {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun `should produce dependency with default type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9"
            // Don't include type for this test as it's implicit
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.defaultTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with jar type omitted from XML`() {
        // Given
        val expectedNoJarType = "<dependency> " +
                "<groupId>org.springframework</groupId> " +
                "<artifactId>spring-core</artifactId> " +
                "<version>5.3.9</version> " +
                "</dependency>"

        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            type = "jar"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(expectedNoJarType), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with war type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "sample-webapp",
            version = "1.0.0",
            type = "war"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.warTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with ear type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "enterprise-app",
            version = "1.0.0",
            type = "ear"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.earTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with pom type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework.boot",
            artifactId = "spring-boot-dependencies",
            version = "2.6.3",
            type = "pom"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.pomTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with maven-plugin type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.apache.maven.plugins",
            artifactId = "maven-compiler-plugin",
            version = "3.10.1",
            type = "maven-plugin"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.mavenPluginTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with test-jar type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "library-with-test-utils",
            version = "1.0.0",
            type = "test-jar"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.testJarTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with ejb type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "enterprise-bean",
            version = "1.0.0",
            type = "ejb"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.ejbTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with bundle type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "osgi-bundle",
            version = "1.0.0",
            type = "bundle"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.bundleTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with zip type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "resources-package",
            version = "1.0.0",
            type = "zip"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.zipTypeXml), normalizeXml(result))
    }
} 