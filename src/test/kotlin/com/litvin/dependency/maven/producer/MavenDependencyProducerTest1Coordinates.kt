package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.maven.reference.MavenParserTest1Coordinates
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.litvin.dependency.util.XmlTestUtils.normalizeXml

/**
 * Tests that validate producing of various Maven coordinate formats using MavenDependencyProducer
 */
class MavenDependencyProducerTest1Coordinates {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun `should produce Spring Core XML correctly`() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest1Coordinates.springCoreXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce Guava XML correctly`() {
        val dependency = DependencyModel(
            groupId = "com.google.guava",
            artifactId = "guava",
            version = "31.1-jre",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest1Coordinates.guavaXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce Commons Lang XML correctly`() {
        val dependency = DependencyModel(
            groupId = "org.apache.commons",
            artifactId = "commons-lang3",
            version = "3.12.0",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest1Coordinates.commonsLangXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce company module XML correctly`() {
        val dependency = DependencyModel(
            groupId = "com.mycompany.project",
            artifactId = "module-name",
            version = "1.0.0",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest1Coordinates.companyModuleXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce Spring Boot starter XML correctly`() {
        val dependency = DependencyModel(
            groupId = "org.springframework.boot",
            artifactId = "spring-boot-starter",
            version = "2.6.3",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest1Coordinates.springBootStarterXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce Quarkus Core XML correctly`() {
        val dependency = DependencyModel(
            groupId = "io.quarkus",
            artifactId = "quarkus-core",
            version = "2.7.5.Final",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest1Coordinates.quarkusCoreXml), normalizeXml(result))
    }
} 