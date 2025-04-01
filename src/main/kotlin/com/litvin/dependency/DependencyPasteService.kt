package com.litvin.dependency

import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.Logger

@Service
class DependencyPasteService : Disposable {

    private val logger = Logger.getInstance(DependencyPasteService::class.java)
    
    init {
        logger.info("✅ DependencyPasteService initialized")
        val messageBus = ApplicationManager.getApplication().messageBus
        val listener = DependencyPasteListener()
        messageBus.connect(this).subscribe(AnActionListener.TOPIC, listener)
        logger.info("✅ DependencyPasteListener registered")
    }
    
    override fun dispose() {
        logger.info("DependencyPasteService disposed")
    }
    
//    companion object {
//        fun getInstance() = service<DependencyPasteService>()
//    }
}

