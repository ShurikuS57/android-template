package io.taptm.tops.screens.mangaTop.presenter

import io.taptm.common.arch.UiEffect
import io.taptm.common.arch.UiEvent
import io.taptm.common.arch.UiState
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.tops.screens.animeTop.presenter.AnimeTopContract.Effect.Navigation
import io.taptm.tops.screens.mangaTop.models.MangaTopEntity

internal data class State(
    val screenState: ScreenState,
    val data: List<MangaTopEntity> = listOf()
) : UiState

internal sealed class Event : UiEvent {
    data object OnLoadData : Event()
}

internal sealed class Effect : UiEffect {
    sealed class Navigation : Effect() {
        data class OpenDetails(val id: String) : Navigation()
    }
}