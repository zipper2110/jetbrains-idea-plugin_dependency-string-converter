package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest5TransitiveDependencies
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class DependencyFormatTest5TransitiveDependencies {
    
    @Test
    fun `should detect single exclusion as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.singleExclusion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect multiple exclusions as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.multipleExclusions
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect group-only exclusion as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.groupOnlyExclusion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect module-only exclusion as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.moduleOnlyExclusion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect transitive disabled as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.transitiveDisabled
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect capability conflict as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.capabilityConflict
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect dependency substitution as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.dependencySubstitution
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect force version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.forceVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect exclude all transitive dependencies as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.excludeAllTransitive
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect complex combination as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.complexCombination
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect configuration-wide exclusions as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.configurationExclusions
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect dependency constraints as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest5TransitiveDependencies.dependencyConstraints
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 