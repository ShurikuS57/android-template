package io.taptm.tops.screens.details.presentation

import androidx.lifecycle.viewModelScope
import io.taptm.common.arch.BaseMviViewModel
import io.taptm.designsystem.component.scaffold.ScreenState
import io.taptm.navigation.AppNavRoute
import io.taptm.network.models.RepoResult
import io.taptm.tops.screens.details.repo.DetailsRepo
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
internal class DetailsVM(
    @InjectedParam private val args: AppNavRoute.Details,
    private val repo: DetailsRepo
) : BaseMviViewModel<Event, State, Effect>() {


    override fun createInitialState(): State = State(ScreenState.Loading)

    override fun handleEvent(event: Event) {
        when(event) {
            Event.OnLoadData -> loadDate()
        }
    }

    private fun loadDate() {
        viewModelScope.launch {
            when (val result =
                if (args.isAnime) repo.getAnimeDetails(args.id) else repo.getMangaDetails(args.id)) {
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