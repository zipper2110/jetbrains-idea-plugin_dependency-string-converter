package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class BasicConversionTest {
    private val listener = DependencyPasteListener()

    @Test
    fun `should recognize Gradle dependency`() {
        val gradleDependency = """implementation('org.example:lib:1.0')"""
        assertTrue(listener.isGradleDependency(gradleDependency))
    }
} 