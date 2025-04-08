package com.litvin.dependency.util

object GroovyTestUtils {
    /**
     * Normalizes Groovy code by removing extra whitespace and normalizing line endings
     */
    fun normalizeGroovy(groovy: String): String {
        return groovy.trim()
            .replace("\r\n", "\n")
            .replace(Regex("\\s+"), " ")
            .trim()
    }
} 