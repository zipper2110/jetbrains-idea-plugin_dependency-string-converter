package com.litvin.dependency

import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.actions.PasteAction
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.util.ui.TextTransferable
import com.litvin.dependency.converter.DependencyConverterRegistry
import com.litvin.dependency.converter.DependencyTextConverter
import com.litvin.dependency.model.DependencyFormat
import java.awt.datatransfer.DataFlavor

class DependencyPasteListener : AnActionListener {
    private val logger = Logger.getInstance(javaClass)
    private val converter = DependencyTextConverter()

    override fun beforeActionPerformed(action: AnAction, event: AnActionEvent) {
        logger.info("✅ Action performed: ${action.javaClass.name}")

        if (action !is PasteAction) return

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
            val sourceFormat = DependencyConverterRegistry().detectPastedTextFormatByContent(clipboardContents)
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
            val convertedText = converter.convertDependencies(clipboardContents, sourceFormat, targetFormat)

            // Replace clipboard content with converted text
            ApplicationManager.getApplication().invokeLater {
                CopyPasteManager.getInstance().setContents(TextTransferable(StringBuilder(convertedText)))
                logger.info("✅ Clipboard content replaced with converted dependency")
                logger.info("✅ Updated clipboard content: ${clipboardContents.take(50)}${if (clipboardContents.length > 50) "..." else ""}")
                
                // Show notification to the user
                showConversionNotification(project, sourceFormat, targetFormat, clipboardContents, convertedText)
            }
            CopyPasteManager.getInstance().setContents(TextTransferable(StringBuilder(convertedText)))

        } catch (e: Exception) {
            logger.error("❌ Error processing dependency: ${e.message}", e)
        }
    }

    private fun showConversionNotification(
        project: com.intellij.openapi.project.Project,
        sourceFormat: DependencyFormat,
        targetFormat: DependencyFormat,
        originalText: String,
        convertedText: String
    ) {
        val message = createNotificationMessage(sourceFormat, targetFormat, originalText, convertedText)
        
        val notification = NotificationGroupManager.getInstance()
            .getNotificationGroup("Dependency Converter")
            .createNotification(
                "Dependency Converted",
                message,
                NotificationType.INFORMATION
            )

        notification.addAction(NotificationAction.createSimple("Revert") {
            // Restore original clipboard content
            CopyPasteManager.getInstance().setContents(
                TextTransferable(StringBuilder(originalText))
            )
            notification.expire()

            // Show confirmation of revert
            val confirmNotification = NotificationGroupManager.getInstance()
                .getNotificationGroup("Dependency Converter")
                .createNotification(
                    "Original Text Restored",
                    "The clipboard now contains the original text.",
                    NotificationType.INFORMATION
                )

            confirmNotification.notify(project)

            // Auto-expire after 3 seconds
            ApplicationManager.getApplication().executeOnPooledThread {
                Thread.sleep(3000)
                confirmNotification.expire()
            }
        })

        notification.notify(project)
    }
    
    fun createNotificationMessage(
        sourceFormat: DependencyFormat,
        targetFormat: DependencyFormat,
        originalText: String,
        convertedText: String
    ): String {
        return buildString {
            append("Dependency converted from ${sourceFormat.displayName} to ${targetFormat.displayName}\n\n")
            append("Original:\n")
            append(originalText.take(20))
            if (originalText.length > 20) append("...")
            append("\n\nConverted:\n")
            append(convertedText.take(20))
            if (convertedText.length > 20) append("...")
        }
    }
}