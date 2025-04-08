package com.litvin.dependency.gradle.groovy.producer

import com.litvin.dependency.converter.producer.GradleGroovyDependencyProducer
import com.litvin.dependency.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GradleGroovyDependencyProducerTest7Files {
    private val producer = GradleGroovyDependencyProducer()

    @Test
    fun `should produce file dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "file",
            artifactId = "libs/mylib.jar",
            scope = "implementation",
            file = FileConfig(
                type = FileType.SINGLE_FILE,
                path = "libs/mylib.jar"
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation files('libs/mylib.jar')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce file tree dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "fileTree",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(
                type = FileType.FILE_TREE,
                path = "libs",
                includes = listOf("*.jar")
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation fileTree('libs') {
                include '*.jar'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce file tree dependency with multiple includes`() {
        // Given
        val model = DependencyModel(
            groupId = "fileTree",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(
                type = FileType.FILE_TREE,
                path = "libs",
                includes = listOf("*.jar", "*.aar")
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation fileTree('libs') {
                include '*.jar'
                include '*.aar'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce file tree dependency with excludes`() {
        // Given
        val model = DependencyModel(
            groupId = "fileTree",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(
                type = FileType.FILE_TREE,
                path = "libs",
                includes = listOf("*.jar"),
                excludes = listOf("*-sources.jar", "*-javadoc.jar")
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation fileTree('libs') {
                include '*.jar'
                exclude '*-sources.jar'
                exclude '*-javadoc.jar'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce file tree dependency with builtBy`() {
        // Given
        val model = DependencyModel(
            groupId = "fileTree",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(
                type = FileType.FILE_TREE,
                path = "libs",
                includes = listOf("*.jar"),
                builtBy = listOf("generateJars")
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation fileTree('libs') {
                include '*.jar'
                builtBy 'generateJars'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce file tree dependency with multiple builtBy`() {
        // Given
        val model = DependencyModel(
            groupId = "fileTree",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(
                type = FileType.FILE_TREE,
                path = "libs",
                includes = listOf("*.jar"),
                builtBy = listOf("generateJars", "processResources")
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation fileTree('libs') {
                include '*.jar'
                builtBy 'generateJars'
                builtBy 'processResources'
            }
        """.trimIndent(), result)
    }

    @Test
    fun `should produce directory dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "dir",
            artifactId = "libs",
            scope = "implementation",
            file = FileConfig(
                type = FileType.DIRECTORY,
                path = "libs"
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation dir('libs')
        """.trimIndent(), result)
    }

    @Test
    fun `should produce url dependency`() {
        // Given
        val model = DependencyModel(
            groupId = "url",
            artifactId = "https://example.com/mylib.jar",
            scope = "implementation",
            file = FileConfig(
                type = FileType.URL,
                path = "https://example.com/mylib.jar"
            )
        )

        // When
        val result = producer.produce(model)

        // Then
        assertEquals("""
            implementation url('https://example.com/mylib.jar')
        """.trimIndent(), result)
    }
} 