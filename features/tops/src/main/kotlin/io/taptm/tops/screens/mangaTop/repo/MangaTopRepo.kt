package io.taptm.tops.screens.mangaTop.repo

import io.taptm.network.ApiService
import io.taptm.network.models.RepoResult
import io.taptm.network.utils.safeApiCall
import io.taptm.tops.screens.mangaTop.mapper.MangaTopMapper
import io.taptm.tops.screens.mangaTop.models.MangaTopEntity

internal interface MangaTopRepo {
    suspend fun getMangaTop(): RepoResult<List<MangaTopEntity>>
}

internal class MangaTopRepoImpl(
    private val apiService: ApiService,
    private val mapper: MangaTopMapper
) : MangaTopRepo {

    override suspend fun getMangaTop(): RepoResult<List<MangaTopEntity>> {
        return safeApiCall(mapper) { apiService.getTopManga() }
    }

}