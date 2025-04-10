package io.taptm.tops.screens.animeTop.presenter

import androidx.lifecycle.viewModelScope
import io.taptm.common.arch.BaseMviViewModel
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.network.models.RepoResult
import io.taptm.prefs.repos.PrefsRepository
import io.taptm.tops.screens.animeTop.presenter.AnimeTopContract.Effect
import io.taptm.tops.screens.animeTop.presenter.AnimeTopContract.Event
import io.taptm.tops.screens.animeTop.presenter.AnimeTopContract.State
import io.taptm.tops.screens.animeTop.repo.AnimeTopRepo
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class AnimeTopVM(
    private val repo: AnimeTopRepo,
    private val prefs: PrefsRepository,
) : BaseMviViewModel<Event, State, Effect>() {

    init {
        loadData()
    }

    override fun createInitialState(): State = State(
        screenState = ScreenState.Loading
    )

    override fun handleEvent(event: Event) {
        when(event) {
            Event.OnSwitchTheme -> {

            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            when (val result = repo.getAnimeTop()) {
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