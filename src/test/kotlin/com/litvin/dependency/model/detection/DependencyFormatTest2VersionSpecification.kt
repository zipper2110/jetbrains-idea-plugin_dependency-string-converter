package com.litvin.dependency.model.detection

import com.litvin.dependency.gradle.groovy.reference.GradleGroovyTest2VersionSpecification
import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class DependencyFormatTest2VersionSpecification {
    
    @Test
    fun `should detect dynamic latest version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.latestVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect plus notation as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.plusNotation
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect latest minor as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.latestMinor
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect version range inclusive as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.versionRangeInclusive
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect version range exclusive as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.versionRangeExclusive
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect version range mixed as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.versionRangeMixed
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect rich version prefer as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionPrefer
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect rich version reject as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionReject
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect rich version require as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionRequire
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect rich version strictly as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionStrictly
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    @Disabled
    fun `should detect rich version combination as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.richVersionCombination
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect SNAPSHOT version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.snapshotVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect release candidate version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.rcVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect beta version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.betaVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect alpha version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.alphaVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should detect milestone version as Gradle Groovy`() {
        // Given
        val dependency = GradleGroovyTest2VersionSpecification.milestoneVersion
        
        // When
        val format = DependencyFormat.fromContent(dependency)
        
        // Then
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
} 