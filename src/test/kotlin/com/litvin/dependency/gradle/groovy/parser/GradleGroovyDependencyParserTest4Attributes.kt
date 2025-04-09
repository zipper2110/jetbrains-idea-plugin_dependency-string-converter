package com.litvin.dependency.gradle.groovy.parser

import com.litvin.dependency.converter.parser.GradleGroovyDependencyParser
import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest4Attributes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests that validate parsing of various Gradle Groovy dependency attribute formats
 */
class GradleGroovyDependencyParserTest4Attributes {
    private val parser = GradleGroovyDependencyParser()
    
    @Test
    fun `should parse basic attribute correctly`() {
        // Given
        val basicAttribute = GradleGroovyTest4Attributes.basicAttribute
        
        // When
        val result = parser.parse(basicAttribute)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify attributes
        assertTrue(result.config.attributes.isNotEmpty())
        val attribute = result.config.attributes.find { it.key.contains("TARGET_JVM_VERSION_ATTRIBUTE") }
        assertNotNull(attribute)
        assertEquals("11", attribute!!.value)
    }
    
    @Test
    fun `should parse multiple attributes correctly`() {
        // Given
        val multipleAttributes = GradleGroovyTest4Attributes.multipleAttributes
        
        // When
        val result = parser.parse(multipleAttributes)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify attributes
        assertEquals(3, result.config.attributes.size)
        assertTrue(result.config.attributes.any { it.key.contains("TARGET_JVM_VERSION_ATTRIBUTE") && it.value == "11" })
        assertTrue(result.config.attributes.any { it.key.contains("USAGE_ATTRIBUTE") && it.value == "'java-runtime'" })
        assertTrue(result.config.attributes.any { it.key.contains("CATEGORY_ATTRIBUTE") && it.value == "'library'" })
    }
    
    @Test
    fun `should parse custom attribute correctly`() {
        // Given
        val customAttribute = GradleGroovyTest4Attributes.customAttribute
        
        // When
        val result = parser.parse(customAttribute)
        
        // Then
        assertNotNull(result)
        assertEquals("org.springframework", result.groupId)
        assertEquals("spring-core", result.artifactId)
        assertEquals("5.3.9", result.version)
        assertEquals("implementation", result.scope)
        
        // Verify attributes
        assertTrue(result.config.attributes.isNotEmpty())
        val attribute = result.config.attributes.find { it.key.contains("'my.custom.attribute'") }
        assertNotNull(attribute)
        assertEquals("custom-value", attribute!!.value)
    }
} 