package io.taptm.sample.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.taptm.navigation.AppNavRoute
import io.taptm.tops.navigation.topsNavigation

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(AppNavRoute.MainTab(0))
    val onBack = { if (backStack.size > 1) backStack.removeAt(backStack.lastIndex); Unit }
    NavDisplay(
        backStack = backStack,
        onBack = onBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<AppNavRoute.MainTab> { route ->
                TabScreen(arguments = route, openDetails = { backStack.add(it) })
            }
            topsNavigation(PaddingValues(), { backStack.add(it) }, onBack)
        },
    )
}
