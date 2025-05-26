package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest6Platform
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class DependencyFormatTest6Platform {
    
    @Test
    fun `should detect basic platform dependency as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.basicPlatform
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect platform with constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithConstraints
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect platform with exclusions as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithExclusions
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect enforced platform as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.enforcedPlatform
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect platform with capabilities as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithCapabilities
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect custom platform as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.customPlatform
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect platform with dependency constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithDependencyConstraints
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect platform with rich version constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithRichVersions
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect platform with attributes as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.platformWithAttributes
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect multiple platform dependencies as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest6Platform.multiplePlatforms
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 