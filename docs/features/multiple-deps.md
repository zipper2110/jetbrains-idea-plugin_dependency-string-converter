# Multiple Dependencies Support

## Overview

Support for parsing and converting multiple dependencies from a single paste operation. The constraint is that pasted text contains dependencies only in a single format (no mixed formats).

## Feature Requirements

### Core Functionality
- Detect when clipboard content contains multiple dependencies in a single format
- Parse and split multiple dependencies from pasted text
- Convert each dependency to the target format
- Provide graceful error handling for partial failures

### Format Support
- **Gradle (Groovy/Kotlin)**: Multi-line dependencies with configuration blocks
- **Maven**: Multiple `<dependency>` elements with various configurations
- **Single format constraint**: All dependencies must be in the same format

## Complex Dependency Examples

### Gradle Multi-line Configuration Blocks
- Dependencies with exclusion rules
- Transitive dependency settings
- Version catalog references
- Project dependencies
- Configuration blocks spanning multiple lines

### Maven Dependencies with Exclusions
- Dependencies with exclusion elements
- Different scopes (compile, test, provided, etc.)
- Optional dependencies
- Version ranges and properties

### Mixed Content Handling
- Comments between dependencies
- Empty lines and whitespace
- Non-dependency content mixed in
- Incomplete or malformed dependency blocks

## Edge Cases to Handle

### 1. Gradle Multi-line Dependencies
- Configuration blocks spanning multiple lines
- Nested braces and complex syntax
- Different dependency declaration styles
- Version catalog references

### 2. Maven with Comments
- XML comments between dependencies
- Whitespace and formatting variations
- Incomplete dependency blocks
- Namespace declarations

### 3. Error Scenarios
- Malformed dependencies
- Incomplete configuration blocks
- Unbalanced braces or XML tags
- Invalid dependency coordinates

## Key Benefits of Single-Format Constraint

1. **Simplified Detection**: No need to handle format conflicts
2. **Straightforward Splitting**: Predictable parsing patterns
3. **Predictable Error Handling**: Consistent error patterns
4. **Performance Optimized**: Single format detection pass

## Testing Requirements

### Test Scenarios
1. **Single dependency** (existing functionality preservation)
2. **Multiple simple dependencies**
3. **Dependencies with configuration blocks**
4. **Dependencies with comments and whitespace**
5. **Mixed valid/invalid dependencies**
6. **Edge cases with unbalanced braces/tags**
7. **Performance with large dependency lists**

### Test Data Requirements
- Simple multi-line Gradle dependencies
- Complex Gradle dependencies with exclusions and configuration
- Maven dependencies with various scopes and configurations
- Mixed content with comments and whitespace
- Malformed dependencies for error handling validation

## Implementation Considerations

### Performance
- Optimize for large dependency lists
- Efficient parsing algorithms
- Minimal memory footprint during conversion

### User Experience
- Clear feedback on conversion results
- Helpful error messages for failed conversions
- Preserve original formatting where appropriate

### Maintainability
- Extensible architecture for new formats
- Clear separation of concerns
- Comprehensive test coverage 