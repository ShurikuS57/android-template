package io.taptm.tops.screens.details.presentation

import io.taptm.common.arch.UiEffect
import io.taptm.common.arch.UiEvent
import io.taptm.common.arch.UiState
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.tops.screens.details.models.DetailsEntity

internal data class State(
    val screenState: ScreenState,
    val data: DetailsEntity? = null
) : UiState

internal sealed class Event : UiEvent {
    data object OnLoadData : Event()
}

internal sealed class Effect : UiEffect {
    sealed class Navigation : Effect()
}