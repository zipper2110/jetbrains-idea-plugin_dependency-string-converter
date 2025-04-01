package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.maven.reference.MavenParserTest5Classifier
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
    fun testProduceNoClassifier() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9"
            // No classifier
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest5Classifier.noClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceSourcesClassifier() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "sources"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest5Classifier.sourcesClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceJavadocClassifier() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "javadoc"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest5Classifier.javadocClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceTestsClassifier() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "tests"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest5Classifier.testsClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceNativeWindowsClassifier() {
        val dependency = DependencyModel(
            groupId = "org.lwjgl",
            artifactId = "lwjgl",
            version = "3.3.1",
            classifier = "natives-windows"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest5Classifier.nativeWindowsClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceJdk11Classifier() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "custom-library",
            version = "1.0.0",
            classifier = "jdk11"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest5Classifier.jdk11ClassifierXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceJavadocWithType() {
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
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(expected), normalizeXml(result))
    }
    
    @Test
    fun testProduceSourcesWithScope() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "sources",
            scope = "compileOnly" // Translated to 'provided' in Maven
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest5Classifier.sourcesWithScopeXml), normalizeXml(result))
    }
} 