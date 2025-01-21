package io.taptm.designsystemapp.screens.actionButtons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.taptm.designsystem.component.buttom.ActionButton
import io.taptm.designsystem.component.buttom.ActionButtonType
import io.taptm.designsystem.theme.AppTheme

@Composable
fun ActionButtonsScreen() {
    Scaffold { innerPadding ->
        Column(
            Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                type = ActionButtonType.ACCEPT
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "isEnable = false",
                type = ActionButtonType.ACCEPT,
                isEnable = false
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                type = ActionButtonType.SECONDARY
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                isEnable = false,
                type = ActionButtonType.SECONDARY
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                isLoading = true,
                type = ActionButtonType.ACCEPT
            ) {}
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Press me",
                subtitle = "Subtitle",
                isLoading = false,
                type = ActionButtonType.ACCEPT
            ) {}

        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        ActionButtonsScreen()
    }
}