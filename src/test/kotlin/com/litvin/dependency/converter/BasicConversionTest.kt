package com.litvin.dependency.converter

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.DependencyPasteListener
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class BasicConversionTest {
    private val listener = DependencyPasteListener()

    @Test
    fun `should recognize Gradle Kotlin dependency`() {
        val gradleDependency = """implementation("org.example:lib:1.0")"""
        val format = DependencyFormat.fromContent(gradleDependency)
        assertEquals(DependencyFormat.GRADLE_KOTLIN, format)
    }
    
    @Test
    fun `should recognize Gradle Groovy dependency`() {
        val gradleDependency = """implementation 'org.example:lib:1.0'"""
        val format = DependencyFormat.fromContent(gradleDependency)
        assertEquals(DependencyFormat.GRADLE_GROOVY, format)
    }
    
    @Test
    fun `should recognize Maven dependency`() {
        val mavenDependency = """
            <dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>1.0</version>
            </dependency>
        """.trimIndent()
        val format = DependencyFormat.fromContent(mavenDependency)
        assertEquals(DependencyFormat.MAVEN, format)
    }
} 