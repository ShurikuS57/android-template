package io.taptm.sample.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.taptm.navigation.TabNavRoute
import io.taptm.tops.navigation.topsNavigation

@Composable
fun TabNavigationHost(
    tabController: NavHostController,
    appController: NavHostController,
    startDestination: TabNavRoute,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = tabController,
        startDestination = startDestination,
    ) {
        topsNavigation(appController, innerPadding)
    }
}
