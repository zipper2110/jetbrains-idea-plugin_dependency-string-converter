package com.litvin.dependency

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.actions.PasteAction
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.application.ApplicationManager
import com.litvin.dependency.converter.DependencyTextConverter
import com.litvin.dependency.model.DependencyFormat
import java.awt.datatransfer.DataFlavor

class DependencyPasteListener : AnActionListener {
    private val logger = Logger.getInstance(DependencyPasteListener::class.java)
    private val converter = DependencyTextConverter()

    override fun beforeActionPerformed(action: AnAction, event: AnActionEvent) {
        logger.info("✅ Action performed: ${action.javaClass.name}")

        if (action is PasteAction) {
            logger.info("✅ Paste action detected")

            val editor = event.getData(CommonDataKeys.EDITOR)
            val project = event.getData(CommonDataKeys.PROJECT)
            val virtualFile = event.getData(CommonDataKeys.VIRTUAL_FILE)

            if (editor == null || project == null || virtualFile == null) {
                logger.info("❌ Missing required data: editor=${editor != null}, project=${project != null}, file=${virtualFile != null}")
                return
            }

            try {
                // Get clipboard content
                val clipboardContents: String =
                    CopyPasteManager.getInstance().getContents(DataFlavor.stringFlavor) ?: return

                logger.info("✅ Clipboard content: ${clipboardContents.take(50)}${if (clipboardContents.length > 50) "..." else ""}")

                // Detect source format
                val sourceFormat = DependencyFormat.fromContent(clipboardContents)
                if (sourceFormat == null) {
                    logger.info("❌ Not a recognized dependency format")
                    return
                }

                logger.info("✅ Detected source format: $sourceFormat")

                // Determine target format based on current file
                val fileName = virtualFile.name
                val targetFormat = try {
                    DependencyFormat.fromFileName(fileName)
                } catch (e: IllegalArgumentException) {
                    logger.info("❌ Unsupported file type: $fileName")
                    return
                }

                logger.info("✅ Target format: $targetFormat")

                // Skip if formats are the same
                if (sourceFormat == targetFormat) {
                    logger.info("✅ Source and target formats are the same, no conversion needed")
                    return
                }

                // Convert dependency
                val convertedText = converter.convertDependency(clipboardContents, sourceFormat, targetFormat)

                // Replace clipboard content with converted text
                ApplicationManager.getApplication().invokeLater {
                    CopyPasteManager.getInstance().setContents(object : java.awt.datatransfer.StringSelection(convertedText) {})
                    logger.info("✅ Clipboard content replaced with converted dependency")
                }

            } catch (e: Exception) {
                logger.error("❌ Error processing dependency: ${e.message}", e)
            }
        }
    }
}