package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.ExclusionModel
import com.litvin.dependency.maven.reference.MavenParserTest7ExclusionsOptional
import com.litvin.dependency.util.XmlTestUtils.normalizeXml
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Tests that validate producing of Maven dependencies with exclusions and optional flags
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest7ExclusionsOptional {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun testProduceSingleExclusion() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            exclusions = listOf(
                ExclusionModel(
                    groupId = "commons-logging",
                    artifactId = "commons-logging"
                )
            )
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.singleExclusionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceMultipleExclusions() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            exclusions = listOf(
                ExclusionModel(
                    groupId = "commons-logging",
                    artifactId = "commons-logging"
                ),
                ExclusionModel(
                    groupId = "log4j",
                    artifactId = "log4j"
                )
            )
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.multipleExclusionsXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceWildcardExclusion() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            exclusions = listOf(
                ExclusionModel(
                    groupId = "commons-logging",
                    artifactId = "*"
                )
            )
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.wildcardExclusionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceOptionalDependency() {
        val dependency = DependencyModel(
            groupId = "com.h2database",
            artifactId = "h2",
            version = "2.1.210",
            optional = true
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.optionalDependencyXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceExclusionsAndOptional() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-context",
            version = "5.3.9",
            optional = true,
            exclusions = listOf(
                ExclusionModel(
                    groupId = "commons-logging",
                    artifactId = "commons-logging"
                )
            )
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.exclusionsAndOptionalXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceExclusionWithScopeAndType() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-context",
            version = "5.3.9",
            scope = "testImplementation", // Will be mapped to 'test'
            type = "jar",
            exclusions = listOf(
                ExclusionModel(
                    groupId = "commons-logging",
                    artifactId = "commons-logging"
                )
            )
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.exclusionWithScopeAndTypeXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceOptionalWithClassifier() {
        val dependency = DependencyModel(
            groupId = "org.postgresql",
            artifactId = "postgresql",
            version = "42.3.1",
            classifier = "jre7",
            optional = true
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.optionalWithClassifierXml), normalizeXml(result))
    }
} 