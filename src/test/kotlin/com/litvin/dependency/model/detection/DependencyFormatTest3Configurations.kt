package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest3Configurations
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class DependencyFormatTest3Configurations {
    
    @Test
    fun `should detect implementation configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.implementation
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect API configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.api
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect compileOnly configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.compileOnly
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect runtimeOnly configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.runtimeOnly
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect testImplementation configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.testImplementation
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect testCompileOnly configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.testCompileOnly
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect testRuntimeOnly configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.testRuntimeOnly
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect annotationProcessor configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.annotationProcessor
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect kapt configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.kapt
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect debug configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.debugImplementation
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect release configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.releaseImplementation
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect custom configuration as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.customConfiguration
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect multiple configurations as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest3Configurations.multipleConfigurations
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 