package io.taptm.designsystemapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.taptm.designsystemapp.screens.actionButtons.ActionButtonsScreen
import io.taptm.designsystemapp.screens.menu.MainMenuScreen

@Composable
fun DesignAppNavigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = DesignSystemRoute.MainMenu,
    ) {
        composable<DesignSystemRoute.MainMenu> {
            MainMenuScreen(navController)
        }

        composable<DesignSystemRoute.ActionButton> {
            ActionButtonsScreen()
        }
    }

}