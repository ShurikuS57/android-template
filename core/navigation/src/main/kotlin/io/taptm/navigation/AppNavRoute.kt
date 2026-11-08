package io.taptm.navigation

import androidx.annotation.Keep
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Keep
@Serializable
sealed class AppNavRoute : NavKey {

    @Serializable
    data class MainTab(val tabIndex: Int) : AppNavRoute()

    @Serializable
    data class Details(val id: String, val isAnime: Boolean) : AppNavRoute()

}