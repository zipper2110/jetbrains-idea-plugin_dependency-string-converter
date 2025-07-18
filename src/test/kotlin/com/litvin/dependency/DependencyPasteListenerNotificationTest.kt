package com.litvin.dependency

import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DependencyPasteListenerNotificationTest {
    
    private val listener = DependencyPasteListener()
    
    @Test
    fun `should create notification message with format names`() {
        // Given
        val sourceFormat = DependencyFormat.MAVEN
        val targetFormat = DependencyFormat.GRADLE_GROOVY
        
        // When
        val message = listener.createNotificationMessage(sourceFormat, targetFormat)
        
        // Then
        assertTrue(message.contains("Maven"))
        assertTrue(message.contains("Gradle Groovy"))
    }
} 