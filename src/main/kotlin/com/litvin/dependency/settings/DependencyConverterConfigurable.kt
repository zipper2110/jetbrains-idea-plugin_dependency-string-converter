package com.litvin.dependency.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import java.awt.BorderLayout
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Configurable for the Dependency Converter plugin settings.
 * Provides a UI for enabling/disabling the plugin for the current project.
 */
class DependencyConverterConfigurable(private val project: Project) : Configurable {
    private var disablePluginCheckbox: JCheckBox? = null
    private var settings: DependencyConverterProjectSettings = DependencyConverterProjectSettings.getInstance(project)

    override fun getDisplayName(): String = "Dependency Converter"

    override fun createComponent(): JComponent {
        val panel = JPanel(BorderLayout())
        disablePluginCheckbox = JCheckBox("Disable Dependency Converter for this project")
        panel.add(disablePluginCheckbox!!, BorderLayout.NORTH)
        return panel
    }

    override fun isModified(): Boolean {
        return disablePluginCheckbox?.isSelected != settings.isDisabled()
    }

    override fun apply() {
        disablePluginCheckbox?.let {
            settings.setDisabled(it.isSelected)
        }
    }

    override fun reset() {
        disablePluginCheckbox?.isSelected = settings.isDisabled()
    }

    override fun disposeUIResources() {
        disablePluginCheckbox = null
    }
}