# Dependency String Converter

A plugin for JetBrains IntelliJ IDEA to convert pasted dependencies between different dependency managers, such as Gradle and Maven.

## Features

- Convert Maven dependencies to Gradle format (implementation, api, etc.)
- Convert Gradle dependencies to Maven format
- Support for various dependency scopes
- Automatic detection of dependency format

## Installation

- Using the JetBrains Plugin Repository within your IDE (search for "Dependency String Converter")
- Download the plugin JAR from GitHub releases and install it manually

## Usage

1. Copy a dependency string from Maven or Gradle build file
2. Paste it in your IDE
3. The plugin will automatically convert the dependency to the target format based on your current file type

## Development

This project uses Gradle with the Gradle IntelliJ Plugin to build and test the plugin.

### Prerequisites

- JDK 17 or newer
- IntelliJ IDEA (Community or Ultimate)

### Building

```bash
./gradlew build
```

### Running in development mode

```bash
./gradlew runIde
```

## License

MIT License 