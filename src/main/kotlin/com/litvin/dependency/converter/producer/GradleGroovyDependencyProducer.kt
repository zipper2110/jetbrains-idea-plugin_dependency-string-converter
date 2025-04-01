package com.litvin.dependency.converter.producer

import com.litvin.dependency.model.DependencyFormat
import com.litvin.dependency.model.DependencyModel

class GradleGroovyDependencyProducer : DependencyProducer {
    override val targetFormat = DependencyFormat.GRADLE_GROOVY
    
    override fun produce(model: DependencyModel): String {
        return "${model.scope} '${model.groupId}:${model.artifactId}:${model.version}'"
    }
} 