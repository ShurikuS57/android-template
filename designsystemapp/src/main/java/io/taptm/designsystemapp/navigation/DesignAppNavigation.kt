package io.taptm.designsystemapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.taptm.designsystemapp.screens.actionButtons.ActionButtonsScreen
import io.taptm.designsystemapp.screens.menu.MainMenuScreen

@Composable
fun DesignAppNavigation() {
    val backStack = rememberNavBackStack(DesignSystemRoute.MainMenu)
    NavDisplay(
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeAt(backStack.lastIndex) },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<DesignSystemRoute.MainMenu> { MainMenuScreen { backStack.add(it) } }
            entry<DesignSystemRoute.ActionButton> { ActionButtonsScreen() }
        },
    )
}
