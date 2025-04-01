/*
package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class CommentConversionTest {
    private val listener = DependencyPasteListener()

    @Test
    fun `should preserve comments when converting from Gradle to Maven`() {
        val gradleDependency = """// Library for logging
implementation('org.apache.logging.log4j:log4j-core:2.14.1')"""

        val expectedMavenDependency = """<!-- Library for logging -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.14.1</version>
</dependency>"""

        val result = listener.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMavenDependency, result)
    }

    @Test
    fun `should recognize commented Gradle dependency`() {
        val gradleDependency = """// Library for logging
implementation('org.apache.logging.log4j:log4j-core:2.14.1')"""
        assertTrue(listener.isGradleDependency(gradleDependency))
    }
}
*/ 