package io.taptm.designsystemapp.models

import androidx.annotation.DrawableRes
import io.taptm.designsystemapp.navigation.DesignSystemRoute

data class MenuItem(
    val name: String,
    @DrawableRes val image: Int,
    val route: DesignSystemRoute
)