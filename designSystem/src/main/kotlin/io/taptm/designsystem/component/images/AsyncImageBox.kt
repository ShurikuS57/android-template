package io.taptm.designsystem.component.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.taptm.designsystem.ext.inComposePreview
import io.taptm.designsystem.theme.AppTheme

@Composable
fun AsyncImageBox(
    modifier: Modifier = Modifier,
    url: String,
    size: Dp = 48.dp,
    cornerSize: CornerSize = CornerSize(16.dp)
) {
    if (inComposePreview) {
        Box(
            modifier = modifier
                .size(size)
                .clip(RoundedCornerShape(cornerSize))
                .background(AppTheme.systemColor.primary),
        )
    } else {
        AsyncImage(
            model = url,
            modifier = modifier
                .size(size)
                .clip(RoundedCornerShape(cornerSize)),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = ColorPainter(color = AppTheme.systemColor.primary),
        )
    }
}

@Composable
fun AsyncImageFrame(
    modifier: Modifier = Modifier,
    url: String,
    cornerSize: CornerSize = CornerSize(0.dp),
    contentScale: ContentScale = ContentScale.Fit,
) {
    if (inComposePreview) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(cornerSize))
                .background(AppTheme.systemColor.primary),
        )
    } else {
        AsyncImage(
            model = url,
            modifier = modifier
                .clip(RoundedCornerShape(cornerSize)),
            contentDescription = "",
            contentScale = contentScale,
            placeholder = ColorPainter(color = AppTheme.systemColor.primary),
        )
    }
}