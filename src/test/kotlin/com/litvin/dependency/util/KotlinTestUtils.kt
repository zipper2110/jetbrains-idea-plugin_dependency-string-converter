package com.litvin.dependency.util

/**
 * Utility methods for Kotlin DSL testing
 */
object KotlinTestUtils {
    /**
     * Normalizes Kotlin code by removing whitespace and quotes for comparison
     */
    fun normalizeKotlin(code: String): String {
        return code
            .replace("\\s+".toRegex(), " ")
            .replace("\"", "")
            .trim()
    }
} 