package io.taptm.designsystem.component.bottomTab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavBarComponent(
    navItem: List<BottomNavigationItem>,
    selected: Int,
    onNavSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemColors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
) {
    NavigationBar(modifier) {
        navItem.forEachIndexed { index, navItem ->
            NavigationBarItem(
                colors = itemColors,
                selected = selected == index,
                label = { Text(navItem.label) },
                icon = {
                    Icon(
                        navItem.icon,
                        contentDescription = navItem.label
                    )
                },
                onClick = {
                    onNavSelected.invoke(index)
                }
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavBarPreview() {
    BottomNavBarComponent(
        navItem = listOf(
            BottomNavigationItem("Home", Icons.Default.Home),
            BottomNavigationItem("Calendar", Icons.Default.DateRange),
            BottomNavigationItem("Person", Icons.Default.Person),
            BottomNavigationItem("Setting", Icons.Default.Settings)
        ),
        selected = 0,
        onNavSelected = {

        }
    )
}