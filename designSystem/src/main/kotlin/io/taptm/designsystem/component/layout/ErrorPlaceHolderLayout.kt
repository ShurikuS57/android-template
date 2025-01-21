package io.taptm.designsystem.component.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.taptm.common.models.ErrorEntity
import io.taptm.common.models.ErrorType
import io.taptm.designsystem.R
import io.taptm.designsystem.ext.SpacerH
import io.taptm.designsystem.theme.AppTheme
import io.taptm.designsystem.theme.Headline5
import io.taptm.designsystem.theme.Subtitle1

@Composable
fun ErrorPlaceHolderLayout(
    modifier: Modifier = Modifier,
    error: ErrorEntity,
    innerPadding: PaddingValues = PaddingValues(),
    onRefresh: (() -> Unit)? = null,
    onNavigationBack: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .background(AppTheme.colors.bgPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(104.dp),
            painter = painterResource(id = getIcon(error.errorType)),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = error.title,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.Headline5.copy(AppTheme.colors.textColor)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = error.message,
            style = AppTheme.typography.Subtitle1.copy(AppTheme.colors.textColor),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 40.dp)
        )
        if (error.errorType == ErrorType.Connection && onRefresh != null) {
            SpacerH(24.dp)
            Button(onClick = {
                onRefresh.invoke()
            }) {
                Text("Refresh")
            }
        } else if (onNavigationBack != null && error.button.isNotEmpty()) {
            SpacerH(24.dp)
            Button(onClick = {
                onNavigationBack.invoke()
            }) {
                Text("Back")
            }
        }
    }
}

private fun getIcon(type: ErrorType): Int {
    return if (type == ErrorType.Connection) {
        R.drawable.ic_wifi_off
    } else {
        R.drawable.ic_error
    }
}

@Composable
@Preview
private fun ErrorLayoutPreview() {
    AppTheme {
        ErrorPlaceHolderLayout(
            error = ErrorEntity(
                errorType = ErrorType.Server,
                title = "Error",
                message = "Try updating later, Try updating later.",
            ),
        )
    }
}

@Composable
@Preview
private fun ErrorLayoutFromFullScreenPreview() {
    AppTheme {
        ErrorPlaceHolderLayout(
            error = ErrorEntity(
                errorType = ErrorType.Server,
                title = "Error",
                message = "Try updating later.",
                button = "Close"
            ),
            onNavigationBack = {

            }
        )
    }
}

@Composable
@Preview
private fun NetworkErrorLayoutPreview() {
    AppTheme {
        ErrorPlaceHolderLayout(
            error = ErrorEntity(
                errorType = ErrorType.Connection,
                title = "Not connection",
                message = "Check the network settings",
            ),
            onRefresh = {}
        )
    }
}