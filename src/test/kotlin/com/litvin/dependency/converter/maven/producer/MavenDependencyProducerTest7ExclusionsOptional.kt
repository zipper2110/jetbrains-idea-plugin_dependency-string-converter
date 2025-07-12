package com.litvin.dependency.converter.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.converter.maven.reference.MavenParserTest7ExclusionsOptional
import com.litvin.dependency.model.DependencyConfig
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.model.ExclusionModel
import com.litvin.dependency.util.XmlTestUtils.normalizeXml
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of Maven dependencies with exclusions and optional flags
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest7ExclusionsOptional {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun `should produce dependency with single exclusion`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            config = DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "commons-logging"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.singleExclusionXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with multiple exclusions`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            config = DependencyConfig(
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
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.multipleExclusionsXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with wildcard exclusion`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            config = DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "*"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.wildcardExclusionXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce optional dependency`() {
        // Given
        val dependency = DependencyModel(
            groupId = "com.h2database",
            artifactId = "h2",
            version = "2.1.210",
            optional = true
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.optionalDependencyXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with exclusions and optional flag`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-context",
            version = "5.3.9",
            optional = true,
            config = DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "commons-logging"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.exclusionsAndOptionalXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with exclusion scope and type`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-context",
            version = "5.3.9",
            scope = "testImplementation", // Will be mapped to 'test'
            type = "jar",
            config = DependencyConfig(
                exclusions = listOf(
                    ExclusionModel(
                        groupId = "commons-logging",
                        artifactId = "commons-logging"
                    )
                )
            )
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.exclusionWithScopeAndTypeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce optional dependency with classifier`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.postgresql",
            artifactId = "postgresql",
            version = "42.3.1",
            classifier = "jre7",
            optional = true
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest7ExclusionsOptional.optionalWithClassifierXml), normalizeXml(result))
    }
} 