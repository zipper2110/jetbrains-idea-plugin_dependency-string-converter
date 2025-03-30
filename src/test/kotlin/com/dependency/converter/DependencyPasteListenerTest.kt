package com.dependency.converter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class DependencyPasteListenerTest {
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