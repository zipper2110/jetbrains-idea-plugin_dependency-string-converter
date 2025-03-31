package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class ClassifierConversionTest {
    private val listener = DependencyPasteListener()

    @Test
    fun `should convert Maven dependency with classifier and type to Gradle`() {
        val mavenDependency = """<dependency>
    <groupId>org.example</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
    <classifier>sources</classifier>
    <type>jar</type>
</dependency>"""

        val expectedGradleDependency = """implementation('org.example:lib:1.0:sources@jar')"""

        val result = listener.convertMavenToGradle(mavenDependency, false)
        assertEquals(expectedGradleDependency, result)
    }

    @Test
    fun `should convert Maven dependency with only classifier to Gradle`() {
        val mavenDependency = """<dependency>
    <groupId>org.example</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
    <classifier>sources</classifier>
</dependency>"""

        val expectedGradleDependency = """implementation('org.example:lib:1.0:sources')"""

        val result = listener.convertMavenToGradle(mavenDependency, false)
        assertEquals(expectedGradleDependency, result)
    }

    @Test
    fun `should convert Gradle dependency with classifier and type to Maven`() {
        val gradleDependency = """implementation('org.example:lib:1.0:sources@jar')"""

        val expectedMavenDependency = """<dependency>
    <groupId>org.example</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
    <classifier>sources</classifier>
    <type>jar</type>
</dependency>"""

        val result = listener.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMavenDependency, result)
    }

    @Test
    fun `should convert Gradle dependency with only classifier to Maven`() {
        val gradleDependency = """implementation('org.example:lib:1.0:sources')"""

        val expectedMavenDependency = """<dependency>
    <groupId>org.example</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
    <classifier>sources</classifier>
</dependency>"""

        val result = listener.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMavenDependency, result)
    }

    @Test
    fun `should recognize Gradle dependency with classifier`() {
        val gradleDependency = """implementation('org.example:lib:1.0:sources')"""
        assertTrue(listener.isGradleDependency(gradleDependency))
    }
} 