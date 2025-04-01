package com.litvin.dependency.converter.impl

import com.litvin.dependency.converter.DependencyFormat

class GradleGroovyToMavenConverter : GradleToMavenBaseConverter() {
    override val sourceFormat = DependencyFormat.GRADLE_GROOVY
    
    override fun getRegexPattern(): Regex {
        return """.*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s+['"]([^:]+):([^:]+):([^'")\s]+)['"].*""".toRegex()
    }
} 