package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.DependencyFormat

class GradleKotlinDependencyParser : GradleDependencyParser() {
    override val supportedFormat = DependencyFormat.GRADLE_KOTLIN
    
    override val dependencyPattern = Regex(
        "(\\w+)\\s*\\(\\s*[\"']([^:]+):([^:]+):([^\"']+)[\"']\\s*\\)"
    )
} 