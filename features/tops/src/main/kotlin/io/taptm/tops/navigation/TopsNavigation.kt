package io.taptm.tops.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.taptm.navigation.AppNavRoute
import io.taptm.navigation.TabNavRoute
import io.taptm.tops.screens.animeTop.presenter.AnimeTopContract.Effect
import io.taptm.tops.screens.animeTop.presenter.AnimeTopScreen
import io.taptm.tops.screens.details.presentation.DetailsScreen
import io.taptm.tops.screens.mangaTop.presenter.Effect.Navigation
import io.taptm.tops.screens.mangaTop.presenter.MangaTopScreen

fun NavGraphBuilder.topsNavigation(appNavController: NavController, innerPadding: PaddingValues) {

    composable<TabNavRoute.TopAnime> {
        AnimeTopScreen(innerPadding) { navigation ->
            when (navigation) {
                is Effect.Navigation.OpenDetails -> {
                    appNavController.navigate(AppNavRoute.Details(navigation.id, true))
                }
            }
        }
    }

    composable<TabNavRoute.TopManga> {
        MangaTopScreen(innerPadding) { navigation ->
            when (navigation) {
                is Navigation.OpenDetails -> {
                    appNavController.navigate(AppNavRoute.Details(navigation.id, false))
                }
            }
        }
    }

    composable<AppNavRoute.Details> {
        DetailsScreen()
    }
}