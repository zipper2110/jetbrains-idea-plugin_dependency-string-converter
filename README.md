# Dependency String Converter

A plugin for JetBrains IntelliJ IDEA to convert pasted dependencies between different dependency managers, such as Gradle and Maven.

## Features

- Convert Maven dependencies to Gradle format (implementation, api, etc.)
- Convert Gradle dependencies to Maven format
- Support for various dependency scopes
- Automatic detection of dependency format
- Support for dependency exclusions in both formats
- Support for both Groovy and Kotlin DSL in Gradle

## Installation

- Using the JetBrains Plugin Repository within your IDE (search for "Dependency String Converter")
- Download the plugin JAR from GitHub releases and install it manually

## Usage

1. Copy a dependency string from Maven or Gradle build file
2. Paste it in your IDE
3. The plugin will automatically convert the dependency to the target format based on your current file type

### Examples

#### Basic Dependency
Maven:
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.16.0</version>
    <scope>test</scope>
</dependency>
```

Gradle:
```groovy
testImplementation('org.mockito:mockito-core:5.16.0')
```

#### Dependency with Exclusions
Maven:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.2.3</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

Gradle:
```groovy
implementation('org.springframework.boot:spring-boot-starter-web:3.2.3') {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
}
```

Gradle Kotlin DSL:
```kotlin
implementation("org.springframework.boot:spring-boot-starter-web:3.2.3") {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
}
```

## Development

This project uses Gradle with the Gradle IntelliJ Plugin to build and test the plugin.

### Prerequisites

- JDK 17 or newer
- IntelliJ IDEA (Community or Ultimate)

### Building

```bash
./gradlew build
```

### Running IDEA in development mode with bundled plugin

```bash
./gradlew runIde
```
Both debugger and IDEA logs might be used for plugin troubleshooting.

## License

MIT License 