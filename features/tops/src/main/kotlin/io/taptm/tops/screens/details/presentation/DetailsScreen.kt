package io.taptm.tops.screens.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.taptm.designsystem.component.images.AsyncImageFrame
import io.taptm.designsystem.component.scaffold.ScaffoldComponent
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.designsystem.theme.AppTheme
import io.taptm.designsystem.theme.Body1
import io.taptm.network.utils.PreviewProvider
import io.taptm.tops.screens.details.mapper.DetailsMapper
import io.taptm.tops.screens.details.presentation.Effect.Navigation
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun DetailsScreen(
    vm: DetailsVM = koinViewModel(),
    onNavigation: (Navigation) -> Unit
) {
    LaunchedEffect(Unit) {
        vm.setEvent(Event.OnLoadData)
    }
    val state by vm.uiState.collectAsStateWithLifecycle()
    ContentScreen(state, onNavigation)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentScreen(state: State, onNavigation: (Navigation) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var title by remember { mutableStateOf("") }
    ScaffoldComponent(
        screenState = state.screenState,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { onNavigation.invoke(Navigation.OnBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        state.data?.let { data ->
            title = data.title
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(state = rememberScrollState()),
            ) {
                Row(Modifier.padding(16.dp)) {
                    AsyncImageFrame(
                        url = data.posterUrl,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(),
                        cornerSize = CornerSize(18.dp),
                        contentScale = ContentScale.Fit
                    )
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
        ) {}
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
        ) {}
    }
}
