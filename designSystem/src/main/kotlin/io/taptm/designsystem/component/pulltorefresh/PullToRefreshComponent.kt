package io.taptm.designsystem.component.pulltorefresh

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshComponent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    isRefreshing: Boolean,
    onRefresh: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    if (onRefresh != null) {
        val state = rememberPullToRefreshState()
        PullToRefreshBox(
            modifier = modifier,
            state = state,
            isRefreshing = isRefreshing,
            onRefresh = {
                onRefresh.invoke()
            },
            indicator = {
                PullToRefreshDefaults.Indicator(
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = contentPadding.calculateTopPadding()),
                    state = state,
                    isRefreshing = isRefreshing
                )
            }
        ) {
            content()
        }
    } else {
        content()
    }

}