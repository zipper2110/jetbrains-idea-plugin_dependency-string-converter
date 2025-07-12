# Project Conventions

This document outlines the coding, testing, and development conventions used in this project to ensure consistency and maintainability across the codebase.

## Table of Contents
1. [Test Structure](#test-structure)
2. [Test Organization](#test-organization)
3. [Naming Conventions](#naming-conventions)
4. [Common Test Patterns](#common-test-patterns)
5. [Kotlin Style Guide](#kotlin-style-guide)
6. [Documentation](#documentation)
7. [Future Test Categories](#future-test-categories)

## Test Structure

All tests should follow the Given-When-Then pattern to improve readability and maintainability:

```kotlin
@Test
fun `should do something`() {
    // Given
    val input = "some input"

    // When
    val result = systemUnderTest.process(input)

    // Then
    assertEquals(expected, result)
}
```

### Guidelines:

1. **Given**: Set up the test preconditions and inputs
   - Initialize objects
   - Configure mocks
   - Prepare input data

2. **When**: Execute the action being tested
   - Call the method under test
   - Perform the operation that's being verified

3. **Then**: Verify the expected outcomes
   - Assert results
   - Verify mock interactions
   - Check state changes

### Examples:

```kotlin
@Test
fun `should convert Gradle Groovy to Gradle Kotlin`() {
    // Given
    val groovy = """implementation 'org.example:lib:1.0'"""
    val listener = DependencyPasteListener()

    // When
    val kotlin = listener.convertGradleGroovyToKotlin(groovy)

    // Then
    assertEquals("""implementation("org.example:lib:1.0")""", kotlin)
}
```

**Important:** Having the corresponding dedicated comments (`// Given`, `// When`, `// Then`) for sections in test body is mandatory.

## Test Organization

Tests are organized by feature in separate test files. Each test file focuses on a specific aspect of the dependency conversion functionality.

### Test Categories

When adding new features, create a new test file if the feature:
- Has distinct functionality
- Requires multiple test cases
- Has complex conversion logic
- Needs specific validation rules

## Naming Conventions

### Test Files and Classes
1. Test files: `FeatureNameTest.kt`
2. Test classes: `FeatureNameTest`

### Test Methods

- Use backticks for test method names
- Start with "should" to describe the expected behavior
- Be descriptive about what functionality is being tested
- Use nice sentences with whitespaces instead of camelCase to improve readability

Examples:
- `should convert Maven to Gradle format`
- `should handle special characters in dependency names`
- `should throw exception for invalid input`
- `should produce dependency with exact version`
- `should parse dependency with qualifier version`

## Kotlin Style Guide

- Follow the [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use 4 spaces for indentation
- Use trailing commas in multi-line declarations
- Prefer immutable variables (`val` over `var`) when possible
- Use expression bodies for simple functions

## Documentation

- Use KDoc comments for public APIs
- Document exceptions and parameter requirements
- Include example usage for complex functions
