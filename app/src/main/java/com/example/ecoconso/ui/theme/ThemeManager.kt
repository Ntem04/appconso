package com.example.ecoconso.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ThemeManager {
    // Allows toggling dark mode manually or null to follow system theme
    var isDarkModeManual by mutableStateOf<Boolean?>(null)

    fun toggleDarkMode(enabled: Boolean) {
        isDarkModeManual = enabled
    }

    fun resetToSystemDefault() {
        isDarkModeManual = null
    }
}
