package io.taptm.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.koin.core.annotation.Single

@Single
class ApiService(
    private val client: HttpClient
) {
    suspend fun getTopAnime() = client.get("v4/top/anime")

    suspend fun getTopManga() = client.get("v4/top/manga")

    suspend fun getAnimeDetails(id: String) = client.get("v4/anime/$id")

    suspend fun getMangaDetails(id: String) = client.get("v4/manga/$id")

}