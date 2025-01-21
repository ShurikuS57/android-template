package io.taptm.tops.screens.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.taptm.designsystem.component.images.AsyncImageFrame
import io.taptm.designsystem.component.scaffold.ScaffoldComponent
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.designsystem.ext.SpacerW
import io.taptm.designsystem.theme.AppTheme
import io.taptm.designsystem.theme.Body1
import io.taptm.designsystem.theme.Headline4
import io.taptm.network.utils.PreviewProvider
import io.taptm.tops.screens.details.mapper.DetailsMapper
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun DetailsScreen(
    vm: DetailsVM = koinViewModel()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    ContentScreen(state)
}

@Composable
private fun ContentScreen(state: State) {
    ScaffoldComponent(
        screenState = state.screenState,
    ) { innerPadding ->
        state.data?.let { data ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(state = rememberScrollState()),
            ) {
                Row(Modifier.padding(16.dp)) {
                    AsyncImageFrame(
                        url = data.posterUrl,
                        modifier = Modifier.size(150.dp, 250.dp),
                        cornerSize = CornerSize(18.dp)
                    )
                    SpacerW(16.dp)
                    Text(data.title, style = AppTheme.typography.Headline4)
                }
                Text(
                    text = data.description, style = AppTheme.typography.Body1,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val data = PreviewProvider.getData(
        context = LocalContext.current,
        jsonFilePath = "mock/details/200_anime_details.json",
        mapper = DetailsMapper()
    )
    AppTheme {
        ContentScreen(
            state = State(
                screenState = ScreenState.Success,
                data = data
            )
        )
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    AppTheme {
        ContentScreen(
            state = State(
                screenState = ScreenState.Loading
            )
        )
    }
}