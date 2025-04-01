package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.maven.reference.MavenDimension1Coordinates
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Tests that validate producing of various Maven coordinate formats using MavenDependencyProducer
 */
class MavenDependencyProducerTest {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun testProduceSpringCoreXml() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension1Coordinates.springCoreXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceGuavaXml() {
        val dependency = DependencyModel(
            groupId = "com.google.guava",
            artifactId = "guava",
            version = "31.1-jre",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension1Coordinates.guavaXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceCommonsLangXml() {
        val dependency = DependencyModel(
            groupId = "org.apache.commons",
            artifactId = "commons-lang3",
            version = "3.12.0",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension1Coordinates.commonsLangXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceCompanyModuleXml() {
        val dependency = DependencyModel(
            groupId = "com.mycompany.project",
            artifactId = "module-name",
            version = "1.0.0",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension1Coordinates.companyModuleXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceSpringBootStarterXml() {
        val dependency = DependencyModel(
            groupId = "org.springframework.boot",
            artifactId = "spring-boot-starter",
            version = "2.6.3",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension1Coordinates.springBootStarterXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceQuarkusCoreXml() {
        val dependency = DependencyModel(
            groupId = "io.quarkus",
            artifactId = "quarkus-core",
            version = "2.7.5.Final",
            scope = "implementation"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension1Coordinates.quarkusCoreXml), normalizeXml(result))
    }
    
    /**
     * Normalizes XML by removing whitespace between tags to make comparison easier
     */
    private fun normalizeXml(xml: String): String {
        return xml.replace(Regex("\\s+"), " ").trim()
    }
} 