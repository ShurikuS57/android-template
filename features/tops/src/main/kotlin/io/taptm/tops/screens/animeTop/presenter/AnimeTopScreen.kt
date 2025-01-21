package io.taptm.tops.screens.animeTop.presenter

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.taptm.designsystem.component.images.AsyncImageFrame
import io.taptm.designsystem.component.scaffold.ScaffoldComponent
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.designsystem.theme.AppTheme
import io.taptm.network.utils.PreviewProvider
import io.taptm.themeSwicher.ThemeSwitcher
import io.taptm.tops.screens.animeTop.mapper.AnimeTopMapper
import io.taptm.tops.screens.animeTop.presenter.AnimeTopContract.Effect
import io.taptm.tops.screens.animeTop.presenter.AnimeTopContract.State
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AnimeTopScreen(
    innerPadding: PaddingValues,
    vm: AnimeTopVM = koinViewModel(),
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
        topBar = { TopBar() }
    ) { contentPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = contentPadding
        ) {
            items(state.data) { item ->
                ContentRowItem(item.title, item.posterUrl) {
                    onNavigate.invoke(Effect.Navigation.OpenDetails(item.id))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    val activity = LocalActivity.current
    TopAppBar(
        title = {
            Text("Anime")
        }, windowInsets = WindowInsets(0, 0, 0, 0),
        actions = {
            IconButton(onClick = {
                if (activity is ThemeSwitcher) {
                    activity.switchTheme()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Composable
fun ContentRowItem(title: String, url: String, onClick: () -> Unit) {
    Card(
        Modifier
            .padding(8.dp)
            .height(168.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable { onClick.invoke() }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            AsyncImageFrame(url = url, modifier = Modifier.fillMaxSize())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppTheme.systemColor.inversePrimary.copy(alpha = 0.7f))
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    title,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.bodyLarge
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
        jsonFilePath = "mock/top/200_anime_top.json",
        mapper = AnimeTopMapper()
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
private fun PreviewDark() {
    val data = PreviewProvider.getData(
        context = LocalContext.current,
        jsonFilePath = "mock/top/200_anime_top.json",
        mapper = AnimeTopMapper()
    )
    AppTheme(true) {
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