package com.litvin.dependency.converter.detector

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyFormat.Companion.COMMON_GRADLE_CONFIGS

class GradleKotlinDetector : DependencyFormatDetector {

    override val supportedFormat = DependencyFormat.GRADLE_KOTLIN

    override fun isInputSupported(input: String): Boolean {
        return GRADLE_KOTLIN_PATTERN_IMPLEMENTATION.containsMatchIn(input) ||
            GRADLE_KOTLIN_PATTERN_TEST_IMPLEMENTATION.containsMatchIn(input) ||
            input.contains("implementation(") ||
            input.contains("testImplementation(") ||
            COMMON_GRADLE_CONFIGS.any { input.contains("$it(") }
    }

    companion object {
        private val GRADLE_KOTLIN_PATTERN_IMPLEMENTATION = Regex("""implementation\s*\(["']""")
        private val GRADLE_KOTLIN_PATTERN_TEST_IMPLEMENTATION = Regex("""testImplementation\s*\(["']""")

    }
}