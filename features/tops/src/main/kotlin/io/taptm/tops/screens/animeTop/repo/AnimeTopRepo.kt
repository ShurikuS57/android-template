package io.taptm.tops.screens.animeTop.repo

import io.taptm.network.ApiService
import io.taptm.network.models.RepoResult
import io.taptm.network.utils.safeApiCall
import io.taptm.tops.screens.animeTop.mapper.AnimeTopMapper
import io.taptm.tops.screens.animeTop.models.AnimeTopEntity

internal interface AnimeTopRepo {
    suspend fun getAnimeTop(): RepoResult<List<AnimeTopEntity>>
}

internal class AnimeTopRepoImpl(
    private val apiService: ApiService,
    private val mapper: AnimeTopMapper
) : AnimeTopRepo {

    override suspend fun getAnimeTop(): RepoResult<List<AnimeTopEntity>> {
        return safeApiCall(mapper) { apiService.getTopAnime() }
    }

}