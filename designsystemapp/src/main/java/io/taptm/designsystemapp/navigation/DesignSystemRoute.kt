package io.taptm.designsystemapp.navigation

import androidx.annotation.Keep
import io.taptm.designsystemapp.models.MenuItem
import kotlinx.serialization.Serializable

val menuList = listOf(
    MenuItem(
        name = "Action button",
        image = io.taptm.designsystem.R.drawable.ic_error,
        route = DesignSystemRoute.ActionButton
    )
)


@Keep
@Serializable
sealed class DesignSystemRoute {

    @Serializable
    data object MainMenu : DesignSystemRoute()

    @Serializable
    data object ActionButton : DesignSystemRoute()

}

