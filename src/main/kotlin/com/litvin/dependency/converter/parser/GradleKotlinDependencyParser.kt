package com.litvin.dependency.converter.parser

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class GradleKotlinDependencyParser : DependencyParser {

    override val supportedFormat = DependencyFormat.GRADLE_KOTLIN

    override fun parse(text: String): DependencyModel {
        TODO("Not yet implemented")
    }

    val dependencyPattern = Regex(
        "(\\w+)\\s*\\(\\s*[\"']([^:]+):([^:]+):([^\"']+)[\"']\\s*\\)"
    )
} 