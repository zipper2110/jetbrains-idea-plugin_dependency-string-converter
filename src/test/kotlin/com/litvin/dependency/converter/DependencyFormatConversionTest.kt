package com.litvin.dependency.converter

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.DependencyPasteListener
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DependencyFormatConversionTest {
    private val variableConverter = DependencyTextConverter()

    // MAVEN to GRADLE_KOTLIN conversion tests
    @Test
    fun `convert Maven to Gradle Kotlin - basic dependency`() {
        // Given
        val maven = """
            <dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>1.0</version>
            </dependency>
        """.trimIndent()
        
        // When
        val kotlin = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_KOTLIN)
        
        // Then
        assertEquals("""implementation("org.example:lib:1.0")""", kotlin)
    }
    
    @Test
    fun `convert Maven to Gradle Kotlin - with scope`() {
        // Given
        val maven = """
            <dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>1.0</version>
                <scope>test</scope>
            </dependency>
        """.trimIndent()
        
        // When
        val kotlin = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_KOTLIN)
        
        // Then
        assertEquals("""testImplementation("org.example:lib:1.0")""", kotlin)
    }
    
    @Test
    fun `convert Maven to Gradle Kotlin - with exclusion`() {
        // Given
        val maven = """
            <dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>1.0</version>
                <exclusions>
                    <exclusion>
                        <groupId>commons-logging</groupId>
                        <artifactId>commons-logging</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
        """.trimIndent()
        
        // When
        val kotlin = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_KOTLIN)
        
        // Then
        assertTrue(kotlin.contains("""implementation("org.example:lib:1.0")"""))
        assertTrue(kotlin.contains("""exclude(group = "commons-logging", module = "commons-logging")"""))
    }
    
    // MAVEN to GRADLE_GROOVY conversion tests
    @Test
    fun `convert Maven to Gradle Groovy - basic dependency`() {
        // Given
        val maven = """
            <dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>1.0</version>
            </dependency>
        """.trimIndent()
        
        // When
        val groovy = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_GROOVY)
        
        // Then
        assertEquals("""implementation 'org.example:lib:1.0'""", groovy)
    }
    
    @Test
    fun `convert Maven to Gradle Groovy - with scope`() {
        // Given
        val maven = """
            <dependency>
                <groupId>org.example</groupId>
                <artifactId>lib</artifactId>
                <version>1.0</version>
                <scope>provided</scope>
            </dependency>
        """.trimIndent()
        
        // When
        val groovy = variableConverter.convertMavenToGradle(maven, DependencyFormat.GRADLE_GROOVY)
        
        // Then
        assertEquals("""compileOnly 'org.example:lib:1.0'""", groovy)
    }
    
    // GRADLE_KOTLIN to MAVEN conversion tests
    @Test
    fun `convert Gradle Kotlin to Maven - basic dependency`() {
        // Given
        val kotlin = """implementation("org.example:lib:1.0")"""
        
        // When
        val maven = variableConverter.convertGradleToMaven(kotlin, DependencyFormat.GRADLE_KOTLIN)
        
        // Then
        assertTrue(maven.contains("<groupId>org.example</groupId>"))
        assertTrue(maven.contains("<artifactId>lib</artifactId>"))
        assertTrue(maven.contains("<version>1.0</version>"))
    }
    
    // GRADLE_GROOVY to MAVEN conversion tests
    @Test
    fun `convert Gradle Groovy to Maven - basic dependency`() {
        // Given
        val groovy = """implementation 'org.example:lib:1.0'"""
        
        // When
        val maven = variableConverter.convertGradleToMaven(groovy, DependencyFormat.GRADLE_GROOVY)
        
        // Then
        assertTrue(maven.contains("<groupId>org.example</groupId>"))
        assertTrue(maven.contains("<artifactId>lib</artifactId>"))
        assertTrue(maven.contains("<version>1.0</version>"))
    }
    
    // GRADLE_GROOVY to GRADLE_KOTLIN conversion tests
    @Test
    fun `convert Gradle Groovy to Gradle Kotlin - basic dependency`() {
        // Given
        val groovy = """implementation 'org.example:lib:1.0'"""
        val listener = DependencyPasteListener()
        
        // When
        val kotlin = listener.convertGradleGroovyToKotlin(groovy)
        
        // Then
        assertEquals("""implementation("org.example:lib:1.0")""", kotlin)
    }
    
    @Test
    fun `convert Gradle Groovy to Gradle Kotlin - with exclude`() {
        // Given
        val groovy = """implementation 'org.example:lib:1.0' {
            exclude group: 'commons-logging', module: 'commons-logging'
        }"""
        val listener = DependencyPasteListener()
        
        // When
        val kotlin = listener.convertGradleGroovyToKotlin(groovy)
        
        // Then
        assertTrue(kotlin.contains("""implementation("org.example:lib:1.0")"""))
        assertTrue(kotlin.contains("""exclude(group = "commons-logging", module = "commons-logging")"""))
    }
    
    // GRADLE_KOTLIN to GRADLE_GROOVY conversion tests
    @Test
    fun `convert Gradle Kotlin to Gradle Groovy - basic dependency`() {
        // Given
        val kotlin = """implementation("org.example:lib:1.0")"""
        val listener = DependencyPasteListener()
        
        // When
        val groovy = listener.convertGradleKotlinToGroovy(kotlin)
        
        // Then
        assertEquals("""implementation 'org.example:lib:1.0'""", groovy)
    }
    
    @Test
    fun `convert Gradle Kotlin to Gradle Groovy - with exclude`() {
        // Given
        val kotlin = """implementation("org.example:lib:1.0") {
            exclude(group = "commons-logging", module = "commons-logging")
        }"""
        val listener = DependencyPasteListener()
        
        // When
        val groovy = listener.convertGradleKotlinToGroovy(kotlin)
        
        // Then
        assertTrue(groovy.contains("""implementation 'org.example:lib:1.0'"""))
        assertTrue(groovy.contains("""exclude group: 'commons-logging', module: 'commons-logging'"""))
    }
} 