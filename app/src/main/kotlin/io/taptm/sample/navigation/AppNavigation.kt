package io.taptm.sample.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.taptm.navigation.AppNavRoute
import io.taptm.tops.navigation.topsNavigation

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppNavRoute.MainTab(0),
    ) {
        composable<AppNavRoute.MainTab> { backStackEntry ->
            val tabIndex: AppNavRoute.MainTab = backStackEntry.toRoute()
            TabScreen(appNavController = navController, tabIndex)
        }

        topsNavigation(navController, PaddingValues())
    }
}
