package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest7Capabilities
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class DependencyFormatTest7Capabilities {
    
    @Test
    fun `should detect basic capability as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.basicCapability
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect multiple capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.multipleCapabilities
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect capability with version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect capability with reason as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithReason
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect capability with attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithAttributes
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect capability with version constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithVersionConstraints
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect optional capability as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.optionalCapability
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect capability with configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.capabilityWithConfiguration
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect complex capability combination as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest7Capabilities.complexCapabilityCombination
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 