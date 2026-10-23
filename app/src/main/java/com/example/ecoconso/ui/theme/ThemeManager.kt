package com.example.ecoconso.ui.theme

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ThemeManager {
    var isDarkModeManual by mutableStateOf<Boolean?>(null)
        private set

    private const val PREFERENCES_NAME = "theme_preferences"
    private const val DARK_MODE_KEY = "dark_mode"

    fun initialize(context: Context) {
        val preferences = context.applicationContext
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        isDarkModeManual = if (preferences.contains(DARK_MODE_KEY)) {
            preferences.getBoolean(DARK_MODE_KEY, false)
        } else {
            null
        }
    }

    fun toggleDarkMode(context: Context, enabled: Boolean) {
        isDarkModeManual = enabled
        context.applicationContext
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(DARK_MODE_KEY, enabled)
            .commit()
    }

    fun resetToSystemDefault(context: Context) {
        isDarkModeManual = null
        context.applicationContext
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(DARK_MODE_KEY)
            .commit()
    }
}
