package io.taptm.designsystem.component.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.taptm.designsystem.theme.AppTheme

@Composable
fun LoadingLayout(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding()),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = AppTheme.colors.accent,
            trackColor = Color.White,
            strokeWidth = 2.dp,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        LoadingLayout(innerPadding = PaddingValues(0.dp))
    }
}