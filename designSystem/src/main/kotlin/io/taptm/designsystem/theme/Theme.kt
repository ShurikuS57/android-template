package io.taptm.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val systemColor: ColorScheme
        @Composable
        get() = LocalSystemColors.current

    val typography: Typography
        @Composable
        get() = AppTypography
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val systemColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> SystemDarkColorScheme
        else -> SystemLightColorScheme
    }

    val appColors = if (darkTheme) AppDarkColorScheme else AppLightColorScheme

    ProvideAppColors(systemColorScheme = systemColorScheme, colors = appColors) {
        MaterialTheme(
            colorScheme = systemColorScheme,
            typography = AppTypography,
            content = content
        )
    }
}

@Composable
internal fun ProvideAppColors(
    systemColorScheme: ColorScheme,
    colors: AppColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalSystemColors provides systemColorScheme,
        content = content
    )
}

private val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColorPalette provided")
}

private val LocalSystemColors = staticCompositionLocalOf<ColorScheme> {
    error("No AppColorPalette provided")
}