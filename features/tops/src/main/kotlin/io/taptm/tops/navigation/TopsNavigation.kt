package io.taptm.tops.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import io.taptm.navigation.AppNavRoute
import io.taptm.navigation.TabNavRoute
import io.taptm.tops.screens.animeTop.presenter.AnimeTopContract.Effect
import io.taptm.tops.screens.animeTop.presenter.AnimeTopScreen
import io.taptm.tops.screens.details.presentation.DetailsScreen
import io.taptm.tops.screens.details.presentation.Effect.Navigation.OnBack
import io.taptm.tops.screens.mangaTop.presenter.Effect.Navigation
import io.taptm.tops.screens.mangaTop.presenter.MangaTopScreen

fun EntryProviderScope<NavKey>.topsNavigation(
    innerPadding: PaddingValues,
    openDetails: (AppNavRoute.Details) -> Unit,
    onBack: () -> Unit,
) {
    entry<TabNavRoute.TopAnime> {
        AnimeTopScreen(innerPadding) { navigation ->
            when (navigation) {
                is Effect.Navigation.OpenDetails -> openDetails(AppNavRoute.Details(navigation.id, true))
            }
        }
    }
    entry<TabNavRoute.TopManga> {
        MangaTopScreen(innerPadding) { navigation ->
            when (navigation) {
                is Navigation.OpenDetails -> openDetails(AppNavRoute.Details(navigation.id, false))
            }
        }
    }
    entry<AppNavRoute.Details> { route ->
        DetailsScreen(args = route, onNavigation = { navigation ->
            when (navigation) {
                is OnBack -> onBack()
            }
        })
    }
}
