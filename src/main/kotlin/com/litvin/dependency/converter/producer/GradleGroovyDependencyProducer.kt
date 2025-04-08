package com.litvin.dependency.converter.producer

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class GradleGroovyDependencyProducer : DependencyProducer {
    override val targetFormat = DependencyFormat.GRADLE_GROOVY
    
    override fun produce(model: DependencyModel): String {
        val coordinates = if (model.version != null) {
            "${model.groupId}:${model.artifactId}:${model.version}"
        } else {
            "${model.groupId}:${model.artifactId}"
        }
        return "${model.scope} '$coordinates'"
    }
} 