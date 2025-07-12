package com.litvin.dependency.converter.detector

import com.litvin.dependency.model.DependencyFormat

interface DependencyFormatDetector {

    val supportedFormat: DependencyFormat

    fun isInputSupported(input: String): Boolean
}