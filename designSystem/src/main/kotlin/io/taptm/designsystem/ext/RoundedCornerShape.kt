package io.taptm.designsystem.ext

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ComposableNaming")
@Composable
fun RoundedCornerShapeExt(top: Dp = 0.dp, bottom: Dp = 0.dp) = RoundedCornerShape(
    topStart = CornerSize(top),
    topEnd = CornerSize(top),
    bottomEnd = CornerSize(bottom),
    bottomStart = CornerSize(bottom)
)