package io.taptm.tops.screens.animeTop.mapper

import io.taptm.network.mapper.Mapper
import io.taptm.network.models.responses.topAnime.AnimeTopResponse
import io.taptm.tops.screens.animeTop.models.AnimeTopEntity
import javax.inject.Inject

internal class AnimeTopMapper @Inject constructor() : Mapper<AnimeTopResponse, List<AnimeTopEntity>> {

    override fun toDomain(response: AnimeTopResponse): List<AnimeTopEntity> {
        return response.data.map { data ->
            AnimeTopEntity(
                id = data.id.toString(),
                title = getTitle(data.titles),
                posterUrl = data.images.webp.imageUrl
            )
        }
    }

    private fun getTitle(data: List<AnimeTopResponse.Data.Title>): String {
        if (data.isEmpty()) {
            return ""
        }
        val findTitle = data.find { find -> find.type == "Default" } ?: data.first()
        return findTitle.title
    }

}