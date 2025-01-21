package io.taptm.designsystem.ext

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun PaddingValues.addPadding(vertical: Dp): PaddingValues {
    val top = this.calculateTopPadding()
    val bottom = this.calculateBottomPadding()
    return PaddingValues(start = 0.dp, top = top + vertical, end = 0.dp, bottom = bottom + vertical)
}

fun PaddingValues.addPadding(top: Dp = 0.dp, bottom: Dp = 0.dp): PaddingValues {
    val currentTop = this.calculateTopPadding()
    val currentBottom = this.calculateBottomPadding()
    return PaddingValues(
        start = 0.dp,
        top = currentTop + top,
        end = 0.dp,
        bottom = currentBottom + bottom
    )
}

fun PaddingValues.copy(
    top: Dp = this.calculateTopPadding(),
    bottom: Dp = this.calculateBottomPadding()
): PaddingValues {
    return PaddingValues(
        start = this.calculateTopPadding(),
        top = top,
        end = this.calculateBottomPadding(),
        bottom = bottom
    )
}