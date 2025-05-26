package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest10ArtifactOnly
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class DependencyFormatTest10ArtifactOnly {
    
    @Test
    fun `should detect single file dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.singleFile
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect file tree dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.fileTree
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect file tree with multiple patterns as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.fileTreeMultiplePatterns
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect custom artifact as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.customArtifact
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect multiple artifacts as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.multipleArtifacts
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect directory dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.directory
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect URL dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.url
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect artifact with configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.artifactWithConfiguration
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect complex artifact combination as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest10ArtifactOnly.complexArtifact
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 