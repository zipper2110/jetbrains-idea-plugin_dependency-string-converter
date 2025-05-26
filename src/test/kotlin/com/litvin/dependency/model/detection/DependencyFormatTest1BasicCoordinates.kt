package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest1BasicCoordinates
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DependencyFormatTest1BasicCoordinates {
    
    @Test
    fun `should detect basic string notation as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.basicStringNotation
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect map notation as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.mapNotation
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect API dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.apiDependency
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect compileOnly dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.compileOnlyDependency
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect dependency without version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.noVersionDependency
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect company module dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.companyModuleDependency
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect Spring Boot starter dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.springBootStarterDependency
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect Quarkus dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest1BasicCoordinates.quarkusDependency
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 