package io.taptm.designsystem.component.buttom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.taptm.designsystem.theme.AppTheme
import io.taptm.designsystem.theme.Body1

enum class ActionButtonType(val value: String) {
    ACCEPT("accept"),
    SECONDARY("secondary")
}

open class ActionButtonColors {
    open val bgAccept: Color = Color(0xFF575992)
    open val bgAcceptDisable: Color = Color(0xFF38384D)
    open val bgSecondary: Color = Color(0xFF8D4959)
    open val bgSecondaryDisable: Color = Color(0xFF442330)
    open val textEnable: Color = Color(0xFFFFFFFF)
    open val textDisable: Color = Color(0x80FFFFFF)
    open val subtitleTextEnable = Color(0x80E7E7E7)
    open val subtitleTextDisable = Color(0x80FFFFFF)
    open val progressTrack: Color = Color(0xFFFFFFFF)
}

class ActionButtonColorsDark : ActionButtonColors() {
    override val bgAccept = Color(0xFF353759)
    override val bgAcceptDisable: Color = Color(0xFF38384D)
    override val bgSecondary: Color = Color(0xFF6B3745)
    override val bgSecondaryDisable: Color = Color(0xFF442330)
    override val textEnable: Color = Color(0xFFFFFFFF)
    override val textDisable: Color = Color(0x80FFFFFF)
    override val subtitleTextEnable = Color(0x80E7E7E7)
    override val subtitleTextDisable = Color(0x80FFFFFF)
    override val progressTrack: Color = Color(0xFFFFFFFF)
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    subtitle: String = "",
    type: ActionButtonType = ActionButtonType.ACCEPT,
    isEnable: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = getBgColor(
                type = type,
                isEnable = isEnable
            )
        ),
        modifier = modifier
            .setHeight()
            .setWidth(),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(size = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable(isEnable && !isLoading) {
                    onClick.invoke()
                }
                .setHeight()
                .setWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(16.dp)
                        .height(52.dp / 2 - 8.dp),
                    color = colors.bgAccept,
                    trackColor = colors.progressTrack,
                    strokeWidth = 2.dp,
                )
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = text,
                        style = AppTheme.typography.Body1,
                        color = getTextColor(isEnable),
                    )
                    if (subtitle.isNotBlank()) {
                        Text(
                            text = subtitle,
                            style = AppTheme.typography.Body1,
                            color = getSubtitlesTextColor(isEnable)
                        )
                    }
                }
            }
        }
    }

}

@Composable
private fun getBgColor(type: ActionButtonType, isEnable: Boolean): Color {
    return if (isEnable) {
        when (type) {
            ActionButtonType.ACCEPT -> colors.bgAccept
            ActionButtonType.SECONDARY -> colors.bgSecondary
        }
    } else {
        when (type) {
            ActionButtonType.ACCEPT -> colors.bgAcceptDisable
            ActionButtonType.SECONDARY -> colors.bgSecondaryDisable
        }
    }
}

@Composable
private fun getTextColor(isEnable: Boolean): Color {
    return if (isEnable) {
        colors.textEnable
    } else {
        colors.textDisable
    }
}

@Composable
private fun getSubtitlesTextColor(isEnable: Boolean): Color {
    return if (isEnable) {
        colors.subtitleTextEnable
    } else {
        colors.subtitleTextDisable
    }
}

private fun Modifier.setHeight(): Modifier {
    return this.heightIn(min = 52.dp)
}

private fun Modifier.setWidth(): Modifier {
    return this.fillMaxWidth()
}

private val colors: ActionButtonColors
    @Composable
    get() = if (AppTheme.colors.isDark) ActionButtonColorsDark() else ActionButtonColors()

@Composable
@Preview
private fun Preview() {
    AppTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                type = ActionButtonType.ACCEPT
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "isEnable = false",
                type = ActionButtonType.ACCEPT,
                isEnable = false
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                type = ActionButtonType.SECONDARY
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                isEnable = false,
                type = ActionButtonType.SECONDARY
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                isLoading = true,
                type = ActionButtonType.ACCEPT
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                subtitle = "Subtitle",
                isLoading = false,
                type = ActionButtonType.ACCEPT
            ) {}
        }
    }
}

@Composable
@Preview
private fun PreviewDark() {
    AppTheme(darkTheme = true) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                type = ActionButtonType.ACCEPT
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "isEnable = false",
                type = ActionButtonType.ACCEPT,
                isEnable = false
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                type = ActionButtonType.SECONDARY
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                isEnable = false,
                type = ActionButtonType.SECONDARY
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                isLoading = true,
                type = ActionButtonType.ACCEPT
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                subtitle = "Subtitle",
                isLoading = false,
                type = ActionButtonType.ACCEPT
            ) {}
        }
    }
}