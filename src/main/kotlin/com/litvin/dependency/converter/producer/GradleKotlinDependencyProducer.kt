package com.litvin.dependency.converter.producer

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class GradleKotlinDependencyProducer : DependencyProducer {
    override val targetFormat = DependencyFormat.GRADLE_KOTLIN
    
    override fun produce(model: DependencyModel): String {
        return "${model.scope}(\"${model.groupId}:${model.artifactId}" + (model.version?.let { ":$it"} ?: "") + "\")"
    }
} 