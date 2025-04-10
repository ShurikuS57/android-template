package io.taptm.tops.screens.details.repo

import io.taptm.network.ApiService
import io.taptm.network.models.RepoResult
import io.taptm.network.utils.safeApiCall
import io.taptm.tops.screens.details.mapper.DetailsMapper
import io.taptm.tops.screens.details.models.DetailsEntity
import org.koin.core.annotation.Single

internal interface DetailsRepo {

    suspend fun getAnimeDetails(id: String): RepoResult<DetailsEntity>

    suspend fun getMangaDetails(id: String): RepoResult<DetailsEntity>

}

@Single
internal class DetailsRepoImpl(
    private val apiService: ApiService,
    private val mapper: DetailsMapper
): DetailsRepo {

    override suspend fun getAnimeDetails(id: String): RepoResult<DetailsEntity> {
        return safeApiCall(mapper) { apiService.getAnimeDetails(id) }
    }

    override suspend fun getMangaDetails(id: String): RepoResult<DetailsEntity> {
        return safeApiCall(mapper) { apiService.getMangaDetails(id) }
    }

}