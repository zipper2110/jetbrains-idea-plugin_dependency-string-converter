package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.maven.reference.MavenParserTest3TypePackaging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.litvin.dependency.util.XmlTestUtils.normalizeXml

/**
 * Tests that validate producing of Maven coordinates with various type/packaging formats
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest3TypePackaging {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun testProduceDefaultType() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9"
            // Don't include type for this test as it's implicit
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.defaultTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceJarType() {
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
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(expectedNoJarType), normalizeXml(result))
    }
    
    @Test
    fun testProduceWarType() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "sample-webapp",
            version = "1.0.0",
            type = "war"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.warTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceEarType() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "enterprise-app",
            version = "1.0.0",
            type = "ear"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.earTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProducePomType() {
        val dependency = DependencyModel(
            groupId = "org.springframework.boot",
            artifactId = "spring-boot-dependencies",
            version = "2.6.3",
            type = "pom"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.pomTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceMavenPluginType() {
        val dependency = DependencyModel(
            groupId = "org.apache.maven.plugins",
            artifactId = "maven-compiler-plugin",
            version = "3.10.1",
            type = "maven-plugin"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.mavenPluginTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceTestJarType() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "library-with-test-utils",
            version = "1.0.0",
            type = "test-jar"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.testJarTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceEjbType() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "enterprise-bean",
            version = "1.0.0",
            type = "ejb"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.ejbTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceBundleType() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "osgi-bundle",
            version = "1.0.0",
            type = "bundle"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.bundleTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceZipType() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "resources-package",
            version = "1.0.0",
            type = "zip"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest3TypePackaging.zipTypeXml), normalizeXml(result))
    }
} 