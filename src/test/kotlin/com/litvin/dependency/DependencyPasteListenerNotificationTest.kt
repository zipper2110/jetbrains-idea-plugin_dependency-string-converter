package com.litvin.dependency

import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DependencyPasteListenerNotificationTest {
    
    private val listener = DependencyPasteListener()
    
    @Test
    fun `should create notification message with format names`() {
        // Given
        val sourceFormat = DependencyFormat.MAVEN
        val targetFormat = DependencyFormat.GRADLE_GROOVY
        val originalText = "<dependency><groupId>org.mockito</groupId><artifactId>mockito-core</artifactId><version>5.16.0</version></dependency>"
        val convertedText = "implementation('org.mockito:mockito-core:5.16.0')"
        
        // When
        val message = listener.createNotificationMessage(sourceFormat, targetFormat, originalText, convertedText)
        
        // Then
        assertTrue(message.contains("Maven"))
        assertTrue(message.contains("Gradle Groovy"))
        assertTrue(message.contains("Original:"))
        assertTrue(message.contains("Converted:"))
        assertTrue(message.contains("org.mockito"))
    }
    
    @Test
    fun `should truncate long text in notification`() {
        // Given
        val sourceFormat = DependencyFormat.GRADLE_KOTLIN
        val targetFormat = DependencyFormat.MAVEN
        val longText = "a".repeat(200)
        val convertedText = "b".repeat(200)
        
        // When
        val message = listener.createNotificationMessage(sourceFormat, targetFormat, longText, convertedText)
        
        // Then
        assertTrue(message.contains("..."))
        assertTrue(message.length < 500) // Should be reasonably short
    }
} 