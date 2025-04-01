package com.litvin.dependency.converter.impl

import com.litvin.dependency.converter.DependencyFormat

interface DependencyConversionStrategy {
    fun supports(sourceFormat: DependencyFormat, targetFormat: DependencyFormat): Boolean
    fun convert(text: String): String
}