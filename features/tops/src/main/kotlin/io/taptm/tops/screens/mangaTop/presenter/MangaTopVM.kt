package io.taptm.tops.screens.mangaTop.presenter

import androidx.lifecycle.viewModelScope
import io.taptm.common.arch.BaseMviViewModel
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.network.models.RepoResult
import io.taptm.tops.screens.mangaTop.repo.MangaTopRepo
import kotlinx.coroutines.launch

internal class MangaTopVM(
    private val repo: MangaTopRepo
) : BaseMviViewModel<Event, State, Effect>() {

    init {
        loadData()
    }

    override fun createInitialState(): State = State(screenState = ScreenState.Loading)

    override fun handleEvent(event: Event) {}

    private fun loadData() {
        viewModelScope.launch {
            when (val result = repo.getMangaTop()) {
                is RepoResult.Error -> {
                    setState {
                        currentState.copy(
                            screenState = ScreenState.ErrorPlaceHolder(result.error)
                        )
                    }
                }

                is RepoResult.Success -> {
                    setState {
                        currentState.copy(
                            screenState = ScreenState.Success,
                            data = result.data
                        )
                    }
                }
            }
        }

    }
}