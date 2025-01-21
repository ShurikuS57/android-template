package io.taptm.designsystem.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

internal val AppTypography = Typography(

)

val Typography.Headline1: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 96.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Headline2: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 60.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Headline3: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 48.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Headline4: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 34.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Headline5: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 24.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Headline6: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Subtitle1: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Subtitle2: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Body1: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Body2: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Button: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Caption: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 12.sp,
        color = AppTheme.systemColor.onSurface
    )

val Typography.Overline: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 10.sp,
        color = AppTheme.systemColor.onSurface
    )

@Preview
@Composable
fun TypographyPreview() {
    AppTheme {
        Scaffold { innerPadding ->
            Column(Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                Text("Headline1", style = AppTheme.typography.Headline1)
                Text("Headline2", style = AppTheme.typography.Headline2)
                Text("Headline3", style = AppTheme.typography.Headline3)
                Text("Headline4", style = AppTheme.typography.Headline4)
                Text("Headline5", style = AppTheme.typography.Headline5)
                Text("Headline6", style = AppTheme.typography.Headline6)
                Text("Subtitle1", style = AppTheme.typography.Subtitle1)
                Text("Subtitle2", style = AppTheme.typography.Subtitle2)
                Text("Body1", style = AppTheme.typography.Body1)
                Text("Body2", style = AppTheme.typography.Body2)
                Text("Button", style = AppTheme.typography.Button)
                Text("Caption", style = AppTheme.typography.Caption)
                Text("Overline", style = AppTheme.typography.Overline)
            }
        }
    }
}