package com.litvin.dependency

import com.intellij.openapi.Disposable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DependencyPasteServiceTest {
    
    private lateinit var service: DependencyPasteService
    
    @BeforeEach
    fun setUp() {
        service = DependencyPasteService()
    }
    
    @AfterEach
    fun tearDown() {
        service.dispose()
    }
    
    @Test
    fun `should initialize service properly`() {
        // Given
        // Service is created in setUp()
        
        // When
        // Service is already initialized in setUp()
        
        // Then
        assertNotNull(service)
        assertTrue(service is Disposable)
    }
    
    @Test
    fun `should dispose service without exceptions`() {
        // Given
        // Service is created in setUp()
        
        // When
        val result = runCatching { service.dispose() }
        
        // Then
        assertTrue(result.isSuccess)
    }
}