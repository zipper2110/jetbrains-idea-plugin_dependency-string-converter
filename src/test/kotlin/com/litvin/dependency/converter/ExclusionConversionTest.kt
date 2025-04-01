/*
package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class ExclusionConversionTest {
    private val listener = DependencyPasteListener()

    @Test
    fun `should convert Gradle dependency with exclusion to Maven`() {
        val gradleDependency = """implementation('org.springframework.boot:spring-boot-starter-web') {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
}"""

        val expectedMavenDependency = """<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>None</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>"""

        val result = listener.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMavenDependency, result)
    }

    @Test
    fun `should convert Maven dependency with exclusion to Gradle Groovy`() {
        val mavenDependency = """<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>2.5.0</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>"""

        val expectedGradleDependency = """implementation('org.springframework.boot:spring-boot-starter-web:2.5.0') {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
}"""

        val result = listener.convertMavenToGradle(mavenDependency, false)
        assertEquals(expectedGradleDependency, result)
    }

    @Test
    fun `should convert Maven dependency with multiple exclusions to Gradle Kotlin`() {
        val mavenDependency = """<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>2.5.0</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>"""

        val result = listener.convertMavenToGradle(mavenDependency, true)
        assertTrue(result.contains("exclude(group = \"org.springframework.boot\", module = \"spring-boot-starter-tomcat\")"))
        assertTrue(result.contains("exclude(group = \"org.springframework.boot\", module = \"spring-boot-starter-logging\")"))
    }

    @Test
    fun `should recognize Gradle dependency with exclusion`() {
        val gradleDependency = """implementation('org.springframework.boot:spring-boot-starter-web') {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
}"""
        assertTrue(listener.isGradleDependency(gradleDependency))
    }
}
*/ 