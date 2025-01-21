package io.taptm.network

import android.content.Context
import io.ktor.http.HttpMethod
import io.taptm.mock.MockEntity
import io.taptm.mock.MockUtils.getObjectJson
import io.taptm.network.models.responses.topAnime.AnimeTopResponse

object HttpMockData {

    @Volatile
    private var mockList: List<MockEntity> = listOf()

    fun getMockList(context: Context): List<MockEntity> {
        if (mockList.isEmpty()) {
            mockList = prepareData(context)
        }
        return mockList
    }

    private fun prepareData(context: Context) = listOf(
        MockEntity(
            HttpMethod.Get,
            "v4/example/example",
            getObjectJson<AnimeTopResponse>(context, "mock/top/200_anime_top.json")
        ),
//        MockEntity(
//            HttpMethod.Get,
//            "v4/top/anime",
//            getObjectJson<AnimeTopResponse>(context, "mock/top/200_anime_top.json")
//        ),
//        MockEntity(
//            HttpMethod.Get,
//            "v4/top/manga",
//            getObjectJson<MangaTopResponse>(context, "mock/top/200_manga_top.json")
//        )
    )
}