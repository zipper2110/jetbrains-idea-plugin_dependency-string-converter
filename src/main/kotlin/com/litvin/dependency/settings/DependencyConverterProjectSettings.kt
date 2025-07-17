package com.litvin.dependency.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project

/**
 * Project-level settings for the Dependency Converter plugin.
 * Allows disabling the plugin for specific projects.
 */
@Service(Service.Level.PROJECT)
@State(
    name = "DependencyConverterSettings",
    storages = [Storage("dependencyConverterSettings.xml")]
)
class DependencyConverterProjectSettings : PersistentStateComponent<DependencyConverterProjectSettings.State> {
    
    /**
     * The state class that holds the persistent settings.
     */
    class State {
        var isDisabled: Boolean = false
    }
    
    private var myState = State()
    
    override fun getState(): State = myState
    
    override fun loadState(state: State) {
        myState = state
    }
    
    /**
     * Checks if the plugin is disabled for the current project.
     */
    fun isDisabled(): Boolean = myState.isDisabled
    
    /**
     * Sets whether the plugin is disabled for the current project.
     */
    fun setDisabled(disabled: Boolean) {
        myState.isDisabled = disabled
    }
    
    companion object {
        /**
         * Gets the settings instance for the specified project.
         */
        fun getInstance(project: Project): DependencyConverterProjectSettings {
            return project.getService(DependencyConverterProjectSettings::class.java)
        }
    }
}