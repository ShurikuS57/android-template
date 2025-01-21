package io.taptm.sample.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.taptm.designsystem.component.bottomTab.BottomNavBarComponent
import io.taptm.designsystem.component.bottomTab.BottomNavigationItem
import io.taptm.navigation.AppNavRoute
import io.taptm.navigation.TabNavRoute
import io.taptm.navigation.tabPages

@Composable
fun TabScreen(
    appNavController: NavHostController,
    arguments: AppNavRoute.MainTab,
) {
    val tabNavController: NavHostController = rememberNavController()
    val tabSelected = rememberSaveable { mutableIntStateOf(TabNavRoute.TopAnime.index) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBarComponent(
                navItem = tabPages.map { tab ->
                    BottomNavigationItem(tab.name, getTabIcon(tab))
                },
                selected = tabSelected.intValue,
                onNavSelected = { tabIndex ->
                    tabSelected.intValue = tabIndex
                    tabNavController.navigate(tabPages[tabIndex]) {
                        tabNavController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        TabNavigationHost(
            tabController = tabNavController,
            appController = appNavController,
            startDestination = tabPages.getOrNull(arguments.tabIndex) ?: TabNavRoute.TopAnime,
            innerPadding
        )
    }
}

private fun getTabIcon(tab: TabNavRoute): ImageVector {
    return when(tab) {
        TabNavRoute.TopAnime -> Icons.Default.AccountCircle
        TabNavRoute.TopManga -> Icons.Default.AccountBox
    }
}
