package com.litvin.dependency.converter.detector

import com.litvin.dependency.model.DependencyFormat

class MavenDetector : DependencyFormatDetector {

    override val supportedFormat = DependencyFormat.MAVEN

    override fun isInputSupported(input: String): Boolean {
        return input.contains("<dependency>")
    }
}