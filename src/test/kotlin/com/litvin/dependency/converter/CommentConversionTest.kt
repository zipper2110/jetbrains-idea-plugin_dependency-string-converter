package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class CommentConversionTest {
    private val listener = DependencyPasteListener()

    @Test
    fun `should convert Gradle dependency with comment to Maven`() {
        val gradleDependency = """// https://mvnrepository.com/artifact/org.mockito/mockito-core
testImplementation("org.mockito:mockito-core:5.16.0")"""

        val expectedMavenDependency = """<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.16.0</version>
    <scope>test</scope>
</dependency>"""

        val result = listener.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMavenDependency, result)
    }

    @Test
    fun `should recognize Gradle dependency with comment`() {
        val gradleDependency = """// https://mvnrepository.com/artifact/org.mockito/mockito-core
testImplementation("org.mockito:mockito-core:5.16.0")"""

        assertTrue(listener.isGradleDependency(gradleDependency))
    }
} 