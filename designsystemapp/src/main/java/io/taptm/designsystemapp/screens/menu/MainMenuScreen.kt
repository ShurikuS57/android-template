package io.taptm.designsystemapp.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.taptm.designsystem.theme.AppTheme
import io.taptm.designsystemapp.models.MenuItem
import io.taptm.designsystemapp.navigation.menuList

@Composable
fun MainMenuScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(16.dp),
            contentPadding = innerPadding,
            columns = GridCells.Adaptive(minSize = 100.dp)
        ) {
            items(menuList) { menu ->
                MenuItem(menu, navController)
            }
        }
    }
}

@Composable
private fun MenuItem(item: MenuItem, navController: NavHostController) {
    Card(modifier = Modifier.clickable {
        navController.navigate(item.route)
    }) {
        Column(Modifier.padding(4.dp)) {
            Image(painterResource(item.image), contentDescription = "")
            Text(item.name, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
}


@Preview
@Composable
private fun MainMenuPreview() {
    AppTheme {
        MainMenuScreen(rememberNavController())
    }
}