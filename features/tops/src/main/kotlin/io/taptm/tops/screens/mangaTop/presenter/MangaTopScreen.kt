package io.taptm.tops.screens.mangaTop.presenter

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.taptm.designsystem.component.scaffold.ScaffoldComponent
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.designsystem.theme.AppTheme
import io.taptm.network.utils.PreviewProvider
import io.taptm.tops.screens.animeTop.presenter.ContentRowItem
import io.taptm.tops.screens.mangaTop.mapper.MangaTopMapper
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun MangaTopScreen(
    innerPadding: PaddingValues,
    vm: MangaTopVM = koinViewModel(),
    onNavigate: (Effect.Navigation) -> Unit
) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    ContentScreen(innerPadding, state, onNavigate)
}

@Composable
private fun ContentScreen(
    innerPadding: PaddingValues,
    state: State,
    onNavigate: (Effect.Navigation) -> Unit = {}
) {
    ScaffoldComponent(
        Modifier.padding(innerPadding),
        screenState = state.screenState,
    ) { contentPadding ->
        LazyVerticalGrid(
            contentPadding = contentPadding,
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(state.data) { item ->
                ContentRowItem(item.title, item.posterUrl) {
                    onNavigate.invoke(Effect.Navigation.OpenDetails(item.id))
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val data = PreviewProvider.getData(
        context = LocalContext.current,
        jsonFilePath = "mock/top/200_manga_top.json",
        mapper = MangaTopMapper()
    )
    AppTheme {
        ContentScreen(
            innerPadding = PaddingValues(),
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
            innerPadding = PaddingValues(),
            state = State(
                screenState = ScreenState.Loading
            )
        )
    }
}