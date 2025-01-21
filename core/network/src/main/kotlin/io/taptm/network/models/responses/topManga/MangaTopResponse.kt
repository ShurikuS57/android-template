package io.taptm.network.models.responses.topManga

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaTopResponse(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("pagination")
    val pagination: Pagination
) {
    @Serializable
    data class Data(
        @SerialName("mal_id")
        val id: Long,
        @SerialName("images")
        val images: Images,
        @SerialName("titles")
        val titles: List<Title>,
        @SerialName("url")
        val url: String
    ) {
        @Serializable
        data class Images(
            @SerialName("jpg")
            val jpg: Jpg,
            @SerialName("webp")
            val webp: Webp
        ) {
            @Serializable
            data class Jpg(
                @SerialName("image_url")
                val imageUrl: String,
                @SerialName("large_image_url")
                val largeImageUrl: String,
                @SerialName("small_image_url")
                val smallImageUrl: String
            )

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

    @Serializable
    data class Pagination(
        @SerialName("has_next_page")
        val hasNextPage: Boolean,
        @SerialName("items")
        val items: Items,
        @SerialName("last_visible_page")
        val lastVisiblePage: Int
    ) {
        @Serializable
        data class Items(
            @SerialName("count")
            val count: Int,
            @SerialName("per_page")
            val perPage: Int,
            @SerialName("total")
            val total: Int
        )
    }
}


