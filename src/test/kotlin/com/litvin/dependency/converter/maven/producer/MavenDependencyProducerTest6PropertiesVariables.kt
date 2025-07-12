package com.litvin.dependency.converter.maven.producer

import com.litvin.dependency.converter.maven.reference.MavenParserTest6PropertiesVariables
import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.util.XmlTestUtils.normalizeXml
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests that validate producing of Maven coordinates with properties/variables
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest6PropertiesVariables {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun `should produce dependency with version property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "\${spring.version}"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.versionPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with groupId property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "\${springframework.groupId}",
            artifactId = "spring-core",
            version = "5.3.9"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.groupIdPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with artifactId property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "\${spring.core.artifactId}",
            version = "5.3.9"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.artifactIdPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with multiple properties`() {
        // Given
        val dependency = DependencyModel(
            groupId = "\${springframework.groupId}",
            artifactId = "\${spring.core.artifactId}",
            version = "\${spring.version}"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.multiplePropertiesXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with built-in property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "\${project.version}"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.builtInPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with scope property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "junit",
            artifactId = "junit",
            version = "4.13.2",
            scope = "\${junit.scope}" // This will be mapped to 'test' in Maven
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.scopePropertyXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with classifier property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "\${docs.classifier}"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.classifierPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with system path property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "com.oracle",
            artifactId = "ojdbc8",
            version = "19.3.0",
            systemPath = "\${oracle.jdbc.path}"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.systemPathPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with version range property`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[\${min.version},\${max.version})"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.versionRangePropertyXml), normalizeXml(result))
    }
} 