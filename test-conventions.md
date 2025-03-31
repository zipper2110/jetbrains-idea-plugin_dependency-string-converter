# Test Conventions

## Test Organization

Tests are organized by feature in separate test files. Each test file focuses on a specific aspect of the dependency conversion functionality.

### Current Test Files

1. `BasicConversionTest.kt`
   - Basic dependency recognition
   - Simple dependency format validation
   - Core conversion functionality

2. `CommentConversionTest.kt`
   - Comment handling in dependencies
   - URL comments from Maven Central
   - Comment preservation during conversion

3. `ExclusionConversionTest.kt`
   - Exclusion block handling
   - Conversion between Maven and Gradle exclusion formats
   - Support for both Groovy and Kotlin DSL formats

4. `ClassifierConversionTest.kt`
   - Classifier handling (e.g., sources, javadoc)
   - Type/packaging handling (e.g., jar, war)
   - Combined classifier and type conversion

### Test File Structure

Each test file follows this structure:
```kotlin
class FeatureNameTest {
    private val listener = DependencyPasteListener()

    @Test
    fun `should do something specific`() {
        // Given
        val input = """..."""
        
        // When
        val result = listener.someMethod(input)
        
        // Then
        assertEquals(expected, result)
    }
}
```

### Naming Conventions

1. Test files: `FeatureNameTest.kt`
2. Test classes: `FeatureNameTest`
3. Test methods: `should do something specific`
   - Use backticks for method names
   - Start with "should"
   - Describe the expected behavior

### Test Categories

When adding new features, create a new test file if the feature:
- Has distinct functionality
- Requires multiple test cases
- Has complex conversion logic
- Needs specific validation rules

### Common Test Patterns

1. **Basic Recognition Tests**
   ```kotlin
   @Test
   fun `should recognize feature in input`() {
       assertTrue(listener.hasFeature(input))
   }
   ```

2. **Conversion Tests**
   ```kotlin
   @Test
   fun `should convert from format A to format B`() {
       val result = listener.convert(input)
       assertEquals(expected, result)
   }
   ```

3. **Edge Cases**
   ```kotlin
   @Test
   fun `should handle edge case scenario`() {
       val result = listener.convert(edgeCaseInput)
       assertEquals(expected, result)
   }
   ```

### Future Test Categories

As we add more features, we'll create new test files for:
- Variable Management
- BOM Imports
- Version Management
- Repository Support 