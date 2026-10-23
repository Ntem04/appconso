package com.example.ecoconso.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.ecoconso.ui.theme.ThemeManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcoTopAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    showDarkModeToggle: Boolean = true,
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    val systemInDark = isSystemInDarkTheme()
    val isDark = ThemeManager.isDarkModeManual ?: systemInDark
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Retour",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        actions = {
            if (showDarkModeToggle) {
                IconButton(onClick = {
                    ThemeManager.toggleDarkMode(
                        context,
                        !isDark
                    )
                }) {
                    Icon(
                        imageVector = if (isDark) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                        contentDescription = "Basculer thème",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            actions()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
