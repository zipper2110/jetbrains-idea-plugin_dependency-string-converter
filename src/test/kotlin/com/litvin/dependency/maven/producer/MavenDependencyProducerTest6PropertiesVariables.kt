package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.maven.reference.MavenParserTest6PropertiesVariables
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.litvin.dependency.util.XmlTestUtils.normalizeXml

/**
 * Tests that validate producing of Maven coordinates with property variables
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest6PropertiesVariables {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun testProduceVersionProperty() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "\${spring.version}"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.versionPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceGroupIdProperty() {
        val dependency = DependencyModel(
            groupId = "\${springframework.groupId}",
            artifactId = "spring-core",
            version = "5.3.9"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.groupIdPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceArtifactIdProperty() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "\${spring.core.artifactId}",
            version = "5.3.9"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.artifactIdPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceMultipleProperties() {
        val dependency = DependencyModel(
            groupId = "\${springframework.groupId}",
            artifactId = "\${spring.core.artifactId}",
            version = "\${spring.version}"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.multiplePropertiesXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceBuiltInProperty() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "\${project.version}"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.builtInPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceScopeProperty() {
        val dependency = DependencyModel(
            groupId = "junit",
            artifactId = "junit",
            version = "4.13.2",
            scope = "testImplementation" // This will be mapped to 'test' in Maven
        )
        // For this test, we'll manually check that the scope element is present,
        // but not compare directly since our model uses Gradle-style configurations
        val result = producer.produce(dependency)
        val containsScope = normalizeXml(result).contains("<scope>test</scope>")
        assert(containsScope) { "Expected produced XML to contain <scope>test</scope>" }
    }
    
    @Test
    fun testProduceClassifierProperty() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            classifier = "\${docs.classifier}"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.classifierPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceSystemPathProperty() {
        val dependency = DependencyModel(
            groupId = "com.oracle",
            artifactId = "ojdbc8",
            version = "19.3.0",
            systemPath = "\${oracle.jdbc.path}"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.systemPathPropertyXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeProperty() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[\${min.version},\${max.version})"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest6PropertiesVariables.versionRangePropertyXml), normalizeXml(result))
    }
} 