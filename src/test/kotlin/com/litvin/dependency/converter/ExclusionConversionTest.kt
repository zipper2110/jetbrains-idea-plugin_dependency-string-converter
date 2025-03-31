package com.litvin.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class ExclusionConversionTest {
    private val listener = DependencyPasteListener()

    @Test
    fun `should convert Gradle dependency with exclusions to Maven`() {
        val gradleDependency = """implementation("org.springframework.boot:spring-boot-starter-web:3.2.3") {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
}"""

        val expectedMavenDependency = """<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.2.3</version>
    <scope>compile</scope>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>"""

        val result = listener.convertGradleToMaven(gradleDependency)
        assertEquals(expectedMavenDependency, result)
    }

    @Test
    fun `should convert Maven dependency with exclusions to Gradle`() {
        val mavenDependency = """<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.2.3</version>
    <scope>compile</scope>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>"""

        val expectedGradleDependency = """implementation('org.springframework.boot:spring-boot-starter-web:3.2.3') {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
}"""

        val result = listener.convertMavenToGradle(mavenDependency, false)
        assertEquals(expectedGradleDependency, result)
    }

    @Test
    fun `should convert Maven dependency with exclusions to Gradle Kotlin DSL`() {
        val mavenDependency = """<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.2.3</version>
    <scope>compile</scope>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>"""

        val expectedGradleDependency = """implementation("org.springframework.boot:spring-boot-starter-web:3.2.3") {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
}"""

        val result = listener.convertMavenToGradle(mavenDependency, true)
        assertEquals(expectedGradleDependency, result)
    }

    @Test
    fun `should recognize Gradle dependency with exclusions`() {
        val gradleDependency = """implementation("org.springframework.boot:spring-boot-starter-web:3.2.3") {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
}"""

        assertTrue(listener.isGradleDependency(gradleDependency))
    }
} 