package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DependencyFormatTest {

    @Test
    fun `fromFileName correctly detects GRADLE_KOTLIN format`() {
        // Given
        val fileNames = listOf(
            "build.gradle.kts",
            "module/build.gradle.kts",
            "settings.gradle.kts",
            "test.gradle.kts"
        )
        
        // When/Then
        fileNames.forEach { fileName ->
            assertEquals(DependencyFormat.GRADLE_KOTLIN, DependencyFormat.fromFileName(fileName))
        }
    }
    
    @Test
    fun `fromFileName correctly detects GRADLE_GROOVY format`() {
        // Given
        val fileNames = listOf(
            "build.gradle",
            "module/build.gradle",
            "settings.gradle",
            "test.gradle"
        )
        
        // When/Then
        fileNames.forEach { fileName ->
            assertEquals(DependencyFormat.GRADLE_GROOVY, DependencyFormat.fromFileName(fileName))
        }
    }
    
    @Test
    fun `fromFileName correctly detects MAVEN format`() {
        // Given
        val fileNames = listOf(
            "pom.xml",
            "module/pom.xml",
            "/path/to/pom.xml"
        )
        
        // When/Then
        fileNames.forEach { fileName ->
            assertEquals(DependencyFormat.MAVEN, DependencyFormat.fromFileName(fileName))
        }
    }
    
    @Test
    fun `fromFileName throws exception for unsupported file type`() {
        // Given
        val fileName = "build.sbt"
        
        // When/Then
        assertThrows(IllegalArgumentException::class.java) {
            DependencyFormat.fromFileName(fileName)
        }
    }
    
    @Test
    fun `fromContent correctly detects GRADLE_KOTLIN format`() {
        // Given
        val contents = listOf(
            """implementation("org.example:lib:1.0")""",
            """    implementation("org.example:lib:1.0")""",
            """testImplementation("org.example:lib:1.0") {
                exclude(group = "org.example", module = "excluded")
            }"""
        )
        
        // When/Then
        contents.forEach { content ->
            val result = DependencyFormat.fromContent(content)
            println("GRADLE_KOTLIN test case: '$content' => result: $result")
            assertEquals(DependencyFormat.GRADLE_KOTLIN, result)
        }
    }
    
    @Test
    fun `fromContent correctly detects GRADLE_GROOVY format`() {
        // Given
        val contents = listOf(
            """implementation 'org.example:lib:1.0'""",
            """    implementation 'org.example:lib:1.0'""",
            """testImplementation 'org.example:lib:1.0' {
                exclude group: 'org.example', module: 'excluded'
            }"""
        )
        
        // When/Then
        contents.forEach { content ->
            val result = DependencyFormat.fromContent(content)
            println("GRADLE_GROOVY test case: '$content' => result: $result")
            assertEquals(DependencyFormat.GRADLE_GROOVY, result)
        }
    }
    
    @Test
    fun `fromContent correctly detects MAVEN format`() {
        // Given
        val contents = listOf(
            """<dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>1.0</version>
            </dependency>""",
            """  <dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>1.0</version>
                <scope>test</scope>
            </dependency>"""
        )
        
        // When/Then
        contents.forEach { content ->
            assertEquals(DependencyFormat.MAVEN, DependencyFormat.fromContent(content))
        }
    }
    
    @Test
    fun `fromContent returns null for unrecognized content`() {
        // Given
        val contents = listOf(
            """org.example:lib:1.0""",
            """<project></project>""",
            """// Just a comment""",
            """<artifactId>lib</artifactId>"""
        )
        
        // When/Then
        contents.forEach { content ->
            assertNull(DependencyFormat.fromContent(content))
        }
    }
} 