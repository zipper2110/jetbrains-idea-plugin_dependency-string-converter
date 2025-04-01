package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.maven.reference.MavenParserTest4Scope
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.litvin.dependency.util.XmlTestUtils.normalizeXml

/**
 * Tests that validate producing of Maven coordinates with various scopes
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest4Scope {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun `should produce dependency with default scope`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation" // Default scope in our model
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest4Scope.defaultScopeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with compile scope omitted in XML`() {
        // Given
        val expected = "<dependency> " +
                "<groupId>org.springframework</groupId> " +
                "<artifactId>spring-core</artifactId> " +
                "<version>5.3.9</version> " +
                "</dependency>"

        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9",
            scope = "implementation" // Mapped from compile
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(expected), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with runtime scope`() {
        // Given
        val dependency = DependencyModel(
            groupId = "org.postgresql",
            artifactId = "postgresql",
            version = "42.3.1",
            scope = "runtimeOnly" 
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest4Scope.runtimeScopeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with test scope`() {
        // Given
        val dependency = DependencyModel(
            groupId = "junit",
            artifactId = "junit",
            version = "4.13.2",
            scope = "testImplementation"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest4Scope.testScopeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with provided scope`() {
        // Given
        val dependency = DependencyModel(
            groupId = "javax.servlet",
            artifactId = "javax.servlet-api",
            version = "4.0.1",
            scope = "compileOnly"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest4Scope.providedScopeXml), normalizeXml(result))
    }
    
    @Test
    fun `should produce dependency with system scope and path`() {
        // Given
        val dependency = DependencyModel(
            groupId = "com.oracle",
            artifactId = "ojdbc8",
            version = "19.3.0",
            scope = "implementation", // Mapped from system
            systemPath = "\${basedir}/lib/ojdbc8.jar"
        )
        
        // When
        val result = producer.produce(dependency)
        
        // Then
        assertEquals(normalizeXml(MavenParserTest4Scope.systemScopeXml), normalizeXml(result))
    }
} 