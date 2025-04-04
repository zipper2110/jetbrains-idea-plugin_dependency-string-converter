package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.DependencyFormat

class GradleGroovyDependencyParser : GradleDependencyParser() {
    override val supportedFormat = DependencyFormat.GRADLE_GROOVY
    
    override val dependencyPattern = Regex(
        "(\\w+)\\s+[\"']([^:]+):([^:]+):([^\"']+)[\"']"
    )
} 