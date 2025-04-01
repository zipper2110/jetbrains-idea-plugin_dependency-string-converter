package com.litvin.dependency.converter.impl

import com.litvin.dependency.converter.DependencyFormat

class GradleKotlinToMavenConverter : GradleToMavenBaseConverter() {
    override val sourceFormat = DependencyFormat.GRADLE_KOTLIN
    
    override fun getRegexPattern(): Regex {
        return """.*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s*\(["']([^:]+):([^:]+):([^'")\s]+)['"]\).*""".toRegex()
    }
} 