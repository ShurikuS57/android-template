package io.taptm.tops.screens.mangaTop.mapper

import io.taptm.network.mapper.Mapper
import io.taptm.network.models.responses.topManga.MangaTopResponse
import io.taptm.tops.screens.mangaTop.models.MangaTopEntity

internal class MangaTopMapper : Mapper<MangaTopResponse, List<MangaTopEntity>> {

    override fun toDomain(response: MangaTopResponse): List<MangaTopEntity> {
        return response.data.map { item ->
            MangaTopEntity(
                id = item.id.toString(),
                title = getTitle(item.titles),
                posterUrl = item.images.webp.imageUrl,
                url = item.url
            )
        }
    }


    private fun getTitle(data: List<MangaTopResponse.Data.Title>): String {
        if (data.isEmpty()) {
            return ""
        }
        val findTitle = data.find { find -> find.type == "Default" } ?: data.first()
        return findTitle.title
    }
}
