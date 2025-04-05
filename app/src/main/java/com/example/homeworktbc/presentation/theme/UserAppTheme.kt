package com.example.homeworktbc.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.homeworktbc.presentation.theme.colors.DarkColors
import com.example.homeworktbc.presentation.theme.colors.LightColors
import com.example.homeworktbc.presentation.theme.shape.AppShapes
import com.example.homeworktbc.presentation.theme.typography.AppTypography

@Composable
fun UserAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content,
    )
}