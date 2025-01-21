package io.taptm.network.models.responses.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsResponse(
    @SerialName("data")
    val `data`: Data
) {
    @Serializable
    data class Data(
        @SerialName("images")
        val images: Images,
        @SerialName("synopsis")
        val synopsis: String,
        @SerialName("titles")
        val titles: List<Title>,
        @SerialName("url")
        val url: String,
    ) {
        @Serializable
        data class Images(
            @SerialName("webp")
            val webp: Webp
        ) {
            @Serializable
            data class Webp(
                @SerialName("image_url")
                val imageUrl: String,
                @SerialName("large_image_url")
                val largeImageUrl: String,
                @SerialName("small_image_url")
                val smallImageUrl: String
            )
        }

        @Serializable
        data class Title(
            @SerialName("title")
            val title: String,
            @SerialName("type")
            val type: String
        )
    }
}



