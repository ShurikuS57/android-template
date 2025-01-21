package io.taptm.navigation

import kotlinx.serialization.Serializable


val tabPages = listOf(
    TabNavRoute.TopAnime,
    TabNavRoute.TopManga,
)

@Serializable
sealed class TabNavRoute(val index: Int, val name: String) {

    @Serializable
    data object TopAnime : TabNavRoute(index = 0, name = "Top anime")

    @Serializable
    data object TopManga : TabNavRoute(index = 1, name = "Top Manga")

    fun getRouteName(): String {
        return this.javaClass.canonicalName ?: ""
    }

}