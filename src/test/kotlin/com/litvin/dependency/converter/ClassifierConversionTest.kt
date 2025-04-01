package com.litvin.dependency.converter

import com.litvin.dependency.model.DependencyFormat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ClassifierConversionTest {
    private val converter = DependencyTextConverter()

    @Test
    fun `should convert Maven dependency with classifier and type to Gradle Groovy`() {
        val mavenDependency = """<dependency>
    <groupId>org.example</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
    <classifier>sources</classifier>
    <type>jar</type>
</dependency>"""

        val expectedGradleDependency = """implementation 'org.example:lib:1.0:sources@jar'"""

        val result = converter.convertMavenToGradle(mavenDependency, DependencyFormat.GRADLE_GROOVY)
        assertEquals(expectedGradleDependency, result)
    }

    @Test
    fun `should convert Maven dependency with only classifier to Gradle Kotlin`() {
        val mavenDependency = """<dependency>
    <groupId>org.example</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
    <classifier>sources</classifier>
</dependency>"""

        val expectedGradleDependency = """implementation("org.example:lib:1.0:sources")"""

        val result = converter.convertMavenToGradle(mavenDependency, DependencyFormat.GRADLE_KOTLIN)
        assertEquals(expectedGradleDependency, result)
    }

    @Test
    fun `should convert Gradle Kotlin dependency with classifier and type to Maven`() {
        val gradleDependency = """implementation("org.example:lib:1.0:sources@jar")"""

        val expectedMavenDependency = """<dependency>
    <groupId>org.example</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
    <classifier>sources</classifier>
    <type>jar</type>
</dependency>"""

        val result = converter.convertGradleToMaven(gradleDependency, DependencyFormat.GRADLE_KOTLIN)
        assertEquals(expectedMavenDependency.replace("\r\n", "\n"), result.replace("\r\n", "\n"))
    }

    @Test
    fun `should convert Gradle Groovy dependency with only classifier to Maven`() {
        val gradleDependency = """implementation 'org.example:lib:1.0:sources'"""

        val expectedMavenDependency = """<dependency>
    <groupId>org.example</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
    <classifier>sources</classifier>
</dependency>"""

        val result = converter.convertGradleToMaven(gradleDependency, DependencyFormat.GRADLE_GROOVY)
        assertEquals(expectedMavenDependency.replace("\r\n", "\n"), result.replace("\r\n", "\n"))
    }

    @Test
    fun `should detect format for Gradle dependency with classifier`() {
        val gradleDependency = """implementation("org.example:lib:1.0:sources")"""
        assertEquals(DependencyFormat.GRADLE_KOTLIN, DependencyFormat.fromContent(gradleDependency))
    }
} 