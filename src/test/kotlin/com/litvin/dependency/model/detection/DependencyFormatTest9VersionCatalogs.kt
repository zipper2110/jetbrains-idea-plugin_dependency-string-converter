package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest9VersionCatalogs
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class DependencyFormatTest9VersionCatalogs {
    
    @Test
    fun `should detect basic version catalog reference as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.basicCatalogReference
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect version catalog with configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithConfiguration
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect version catalog bundle as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogBundle
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect version catalog with version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect version catalog with capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithCapabilities
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect version catalog with attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithAttributes
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect multiple catalog references as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.multipleCatalogReferences
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect catalog with rich version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogWithRichVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect complex catalog usage as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.complexCatalogUsage
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect catalog declaration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest9VersionCatalogs.catalogDeclaration
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 