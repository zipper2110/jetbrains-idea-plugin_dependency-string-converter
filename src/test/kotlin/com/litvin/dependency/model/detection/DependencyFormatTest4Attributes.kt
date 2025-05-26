package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest4Attributes
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class DependencyFormatTest4Attributes {
    
    @Test
    fun `should detect basic attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.basicAttribute
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect multiple attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.multipleAttributes
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect custom attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.customAttribute
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect library elements attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.libraryElements
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect bundling attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.bundling
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect documentation type attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.documentationType
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect status attribute as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.status
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect attributes with artifact type as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.attributesWithArtifactType
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect attributes with classifier as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.attributesWithClassifier
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect complex combination of attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest4Attributes.complexCombination
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 