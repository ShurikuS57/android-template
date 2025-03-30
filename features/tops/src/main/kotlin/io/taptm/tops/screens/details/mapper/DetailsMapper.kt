package io.taptm.tops.screens.details.mapper

import io.taptm.network.mapper.Mapper
import io.taptm.network.models.responses.details.DetailsResponse
import io.taptm.tops.screens.details.models.DetailsEntity
import javax.inject.Inject

class DetailsMapper @Inject constructor() : Mapper<DetailsResponse, DetailsEntity> {

    override fun toDomain(response: DetailsResponse): DetailsEntity {
        return DetailsEntity(
            title = getTitle(response.data.titles),
            description = response.data.synopsis,
            posterUrl = response.data.images.webp.imageUrl
        )
    }

    private fun getTitle(data: List<DetailsResponse.Data.Title>): String {
        if (data.isEmpty()) {
            return ""
        }
        val findTitle = data.find { find -> find.type == "Default" } ?: data.first()
        return findTitle.title
    }
}