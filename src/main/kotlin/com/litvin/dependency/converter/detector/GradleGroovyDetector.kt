package com.litvin.dependency.converter.detector

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyFormat.Companion.COMMON_GRADLE_CONFIGS

class GradleGroovyDetector : DependencyFormatDetector {

    override val supportedFormat = DependencyFormat.GRADLE_GROOVY

    override fun isInputSupported(input: String): Boolean {
        return GRADLE_GROOVY_PATTERN_IMPLEMENTATION.containsMatchIn(input) ||
                GRADLE_GROOVY_PATTERN_TEST_IMPLEMENTATION.containsMatchIn(input) ||
                GRADLE_MAP_NOTATION_PATTERN.containsMatchIn(input) ||
                COMMON_GRADLE_CONFIGS.any { input.contains("$it '") }
    }

    companion object {
        private val GRADLE_GROOVY_PATTERN_IMPLEMENTATION = Regex("""implementation\s+['"]""")
        private val GRADLE_GROOVY_PATTERN_TEST_IMPLEMENTATION = Regex("""testImplementation\s+['"]""")
        private val GRADLE_MAP_NOTATION_PATTERN =
            Regex("""(\w+)\s+group:\s*['"]([^'"]+)['"],\s*name:\s*['"]([^'"]+)['"],\s*version:\s*['"]([^'"]+)['"]""")
    }
}