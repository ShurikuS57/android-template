package io.taptm.sample.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.taptm.navigation.AppNavRoute
import io.taptm.navigation.TabNavRoute
import io.taptm.tops.navigation.topsNavigation

@Composable
fun TabNavigationHost(
    selectedTab: TabNavRoute,
    innerPadding: PaddingValues,
    openDetails: (AppNavRoute.Details) -> Unit,
) {
    NavDisplay(
        backStack = listOf<NavKey>(selectedTab),
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            topsNavigation(innerPadding, openDetails, {})
        },
    )
}
