package io.taptm.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val TextColor = Color.Black

val primaryLight = Color(0xFF575992)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFE1E0FF)
val onPrimaryContainerLight = Color(0xFF12144B)
val secondaryLight = Color(0xFF5D5D72)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFE2E0F9)
val onSecondaryContainerLight = Color(0xFF191A2C)
val tertiaryLight = Color(0xFF8D4959)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFFFD9DF)
val onTertiaryContainerLight = Color(0xFF3A0718)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFFCF8FF)
val onBackgroundLight = Color(0xFF1B1B21)
val surfaceLight = Color(0xFFFCF8FF)
val onSurfaceLight = Color(0xFF1B1B21)
val surfaceVariantLight = Color(0xFFDCE5DC)
val onSurfaceVariantLight = Color(0xFF404943)
val outlineLight = Color(0xFF707972)
val outlineVariantLight = Color(0xFFC0C9C1)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF303036)
val inverseOnSurfaceLight = Color(0xFFF3EFF7)
val inversePrimaryLight = Color(0xFFC0C1FF)
val surfaceDimLight = Color(0xFFDCD9E0)
val surfaceBrightLight = Color(0xFFFCF8FF)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFF6F2FA)
val surfaceContainerLight = Color(0xFFF0ECF4)
val surfaceContainerHighLight = Color(0xFFEAE7EF)
val surfaceContainerHighestLight = Color(0xFFE4E1E9)

val primaryDark = Color(0xFFC0C1FF)
val onPrimaryDark = Color(0xFF282A60)
val primaryContainerDark = Color(0xFF3F4178)
val onPrimaryContainerDark = Color(0xFFE1E0FF)
val secondaryDark = Color(0xFFC5C4DD)
val onSecondaryDark = Color(0xFF2E2F42)
val secondaryContainerDark = Color(0xFF454559)
val onSecondaryContainerDark = Color(0xFFE2E0F9)
val tertiaryDark = Color(0xFFFFB1C1)
val onTertiaryDark = Color(0xFF551D2C)
val tertiaryContainerDark = Color(0xFF713342)
val onTertiaryContainerDark = Color(0xFFFFD9DF)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF131318)
val onBackgroundDark = Color(0xFFE4E1E9)
val surfaceDark = Color(0xFF131318)
val onSurfaceDark = Color(0xFFE4E1E9)
val surfaceVariantDark = Color(0xFF404943)
val onSurfaceVariantDark = Color(0xFFC0C9C1)
val outlineDark = Color(0xFF8A938C)
val outlineVariantDark = Color(0xFF404943)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFE4E1E9)
val inverseOnSurfaceDark = Color(0xFF303036)
val inversePrimaryDark = Color(0xFF575992)
val surfaceDimDark = Color(0xFF131318)
val surfaceBrightDark = Color(0xFF39383F)
val surfaceContainerLowestDark = Color(0xFF0E0E13)
val surfaceContainerLowDark = Color(0xFF1B1B21)
val surfaceContainerDark = Color(0xFF1F1F25)
val surfaceContainerHighDark = Color(0xFF2A292F)
val surfaceContainerHighestDark = Color(0xFF35343A)

val AppLightColorScheme = AppColors(
    isDark = false,
    accent = primaryLight,
    bgPrimary = onPrimaryLight,
    textColor = TextColor,
)

val AppDarkColorScheme = AppColors(
    isDark = true,
    accent = primaryDark,
    bgPrimary = onPrimaryDark,
    textColor = TextColor,
)

@Immutable
class AppColors(
    val isDark: Boolean,
    val accent: Color,
    val bgPrimary: Color,
    val textColor: Color,
)

val SystemDarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

val SystemLightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

