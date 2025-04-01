package com.litvin.dependency.util

/**
 * Utility functions for XML processing in tests
 */
object XmlTestUtils {
    /**
     * Normalizes XML by removing whitespace between tags to make comparison easier
     */
    fun normalizeXml(xml: String): String {
        return xml.replace(Regex("\\s+"), "").trim()
    }
} 