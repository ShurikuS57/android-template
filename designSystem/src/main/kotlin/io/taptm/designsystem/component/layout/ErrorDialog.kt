package io.taptm.designsystem.component.layout

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.taptm.common.models.ErrorEntity
import io.taptm.designsystem.component.buttom.ActionButton

@Composable
fun ErrorDialog(error: ErrorEntity?, onBackNavigate: () -> Unit = {}) {
    val showState = remember { mutableStateOf(error != null) }
    if (error != null)
        AlertDialog(
            title = { Text(text = error.title) },
            text = { Text(text = error.message) },
            onDismissRequest = {
                onBackNavigate.invoke()
                showState.value = false
            },
            confirmButton = {
                ActionButton(text = "Понятно") {
                    onBackNavigate.invoke()
                    showState.value = false
                }
            })
}