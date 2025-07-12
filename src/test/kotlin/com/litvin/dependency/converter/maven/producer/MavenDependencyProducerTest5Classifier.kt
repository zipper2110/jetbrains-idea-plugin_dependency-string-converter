package com.litvin.dependency.converter.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.converter.maven.reference.MavenParserTest5Classifier
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.litvin.dependency.util.XmlTestUtils.normalizeXml

/**
 * Tests that validate producing of Maven coordinates with various classifiers
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest5Classifier {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun `should produce dependency without classifier`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9"
            // No classifier
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest5Classifier.noClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with sources classifier`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "sources"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest5Classifier.sourcesClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with javadoc classifier`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "javadoc"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest5Classifier.javadocClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with tests classifier`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "tests"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest5Classifier.testsClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with natives-windows classifier`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.lwjgl",
            artifactId = "lwjgl",
            version = "3.3.1",
            classifier = "natives-windows"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest5Classifier.nativeWindowsClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with jdk11 classifier`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "custom-library",
            version = "1.0.0",
            classifier = "jdk11"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest5Classifier.jdk11ClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with javadoc classifier and jar type`() {
        // Given
        val expected = "<dependency> " +
                "<groupId>org.springframework</groupId> " +
                "<artifactId>spring-core</artifactId> " +
                "<version>5.3.9</version> " +
                "<classifier>javadoc</classifier> " +
                "</dependency>"

        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            type = "jar",
            classifier = "javadoc"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(expected), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with sources classifier and provided scope`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "sources",
            scope = "compileOnly" // Translated to 'provided' in Maven
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest5Classifier.sourcesWithScopeXml), normalizeXml(result))
    }
} 