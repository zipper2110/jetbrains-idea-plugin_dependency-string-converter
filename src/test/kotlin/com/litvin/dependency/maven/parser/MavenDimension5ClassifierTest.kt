package com.litvin.dependency.maven.parser

import com.litvin.dependency.converter.parser.MavenDependencyParser
import com.litvin.dependency.maven.reference.MavenDimension5Classifier
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull

/**
 * Tests that validate parsing of various classifier formats from MavenDimension5Classifier
 */
class MavenDimension5ClassifierTest {
    private val parser = MavenDependencyParser()
    
    @Test
    fun testParseNoClassifier() {
        val result = parser.parse(MavenDimension5Classifier.noClassifierXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertNull(result.classifier)
    }
    
    @Test
    fun testParseSourcesClassifier() {
        val result = parser.parse(MavenDimension5Classifier.sourcesClassifierXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("sources", result.classifier)
    }
    
    @Test
    fun testParseJavadocClassifier() {
        val result = parser.parse(MavenDimension5Classifier.javadocClassifierXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("javadoc", result.classifier)
    }
    
    @Test
    fun testParseTestsClassifier() {
        val result = parser.parse(MavenDimension5Classifier.testsClassifierXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("tests", result.classifier)
    }
    
    @Test
    fun testParseNativeWindowsClassifier() {
        val result = parser.parse(MavenDimension5Classifier.nativeWindowsClassifierXml)
        assertNotNull(result)
        assertEquals("org.lwjgl", result.groupId)
        assertEquals("lwjgl", result.artifactId)
        assertEquals("3.3.1", result.version)
        assertEquals("natives-windows", result.classifier)
    }
    
    @Test
    fun testParseJdk11Classifier() {
        val result = parser.parse(MavenDimension5Classifier.jdk11ClassifierXml)
        assertNotNull(result)
        assertEquals("org.example", result.groupId)
        assertEquals("custom-library", result.artifactId)
        assertEquals("1.0.0", result.version)
        assertEquals("jdk11", result.classifier)
    }
    
    @Test
    fun testParseJavadocWithType() {
        val result = parser.parse(MavenDimension5Classifier.javadocWithTypeXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("jar", result.type)
        assertEquals("javadoc", result.classifier)
    }
    
    @Test
    fun testParseSourcesWithScope() {
        val result = parser.parse(MavenDimension5Classifier.sourcesWithScopeXml)
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("sources", result.classifier)
        assertEquals("compileOnly", result.scope) // 'provided' maps to 'compileOnly'
    }
} 