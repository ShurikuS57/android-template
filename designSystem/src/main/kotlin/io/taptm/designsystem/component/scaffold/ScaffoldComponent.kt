package io.taptm.designsystem.component.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.taptm.common.models.ErrorEntity
import io.taptm.common.models.ErrorType
import io.taptm.designsystem.component.layout.ErrorDialog
import io.taptm.designsystem.component.layout.ErrorPlaceHolderLayout
import io.taptm.designsystem.component.layout.LoadingLayout
import io.taptm.designsystem.component.pulltorefresh.PullToRefreshComponent
import io.taptm.designsystem.ext.RoundedCornerShapeExt
import io.taptm.designsystem.ext.addPadding
import io.taptm.designsystem.theme.AppTheme

@Composable
fun ScaffoldComponent(
    modifier: Modifier = Modifier,
    screenState: ScreenState,
    containerColor: Color = AppTheme.colors.bgPrimary,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    onErrorDialogDismiss: () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    bottomBar: (@Composable () -> Unit)? = null,
    customLoadingLayout: (@Composable() () -> Unit)? = null,
    isPullToRefRefreshing: Boolean = false,
    onPullToRefresh: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = contentWindowInsets,
        topBar = topBar,
        bottomBar = bottomBar ?: {},
        contentColor = containerColor,
        containerColor = containerColor,
    ) { contentPadding ->
        PullToRefreshComponent(
            isRefreshing = isPullToRefRefreshing,
            onRefresh = onPullToRefresh,
            contentPadding = contentPadding
        ) {
            when (screenState) {
                is ScreenState.ErrorPlaceHolder -> {
                    ErrorPlaceHolderLayout(
                        innerPadding = contentPadding
                            .addPadding(
                                top = 8.dp,
                                bottom = if (bottomBar != null) 8.dp else 0.dp
                            ),
                        error = screenState.error,
                        onRefresh = onPullToRefresh
                    )
                }

                ScreenState.Loading -> {
                    if (customLoadingLayout == null) {
                        LoadingLayout(innerPadding = contentPadding)
                    } else {
                        customLoadingLayout()
                    }
                }

                is ScreenState.ErrorDialog -> {
                    ErrorDialog(
                        error = screenState.error,
                        onBackNavigate = {
                            onErrorDialogDismiss.invoke()
                        }
                    )
                    content(contentPadding)
                }

                ScreenState.Success -> {
                    content(contentPadding)
                }

                is ScreenState.ErrorFullScreen -> {
                    // TODO need full screen navigation
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DataPreview() {
    AppTheme {
        ScaffoldComponent(
            screenState = ScreenState.Success,
            onErrorDialogDismiss = {},
            topBar = { TopAppBar(title = { Text("Title") }) },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShapeExt(top = 24.dp))
                        .background(Color.Blue)

                ) {}
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(100) {
                    Spacer(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)

                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoadingPreview() {
    AppTheme {
        ScaffoldComponent(
            screenState = ScreenState.Loading,
            onErrorDialogDismiss = {},
            topBar = { TopAppBar(title = { Text("Title") }) },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShapeExt(top = 24.dp))
                        .background(Color.Blue)

                ) {}
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(100) {
                    Spacer(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)

                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ErrorPreview() {
    AppTheme {
        ScaffoldComponent(
            screenState = ScreenState.ErrorPlaceHolder(
                ErrorEntity(
                    errorType = ErrorType.Server,
                    title = "Произошла ошибка",
                    message = "Попробуйте обновить чуть позже",
                )
            ),
            onErrorDialogDismiss = {},
            topBar = { TopAppBar(title = { Text("Title") }) },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShapeExt(top = 24.dp))
                        .background(Color.Blue)

                ) {}
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(100) {
                    Spacer(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)

                    )
                }
            }
        }
    }
}