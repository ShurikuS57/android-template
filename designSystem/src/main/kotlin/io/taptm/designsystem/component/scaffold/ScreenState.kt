package io.taptm.designsystem.component.scaffold

import io.taptm.common.models.ErrorEntity

sealed class ScreenState {
    data class ErrorPlaceHolder(val error: ErrorEntity) : ScreenState()
    data class ErrorDialog(val error: ErrorEntity) : ScreenState()
    data class ErrorFullScreen(val error: ErrorEntity) : ScreenState()
    data object Loading : ScreenState()
    data object Success : ScreenState()
}

fun ScreenState.isLoading(): Boolean {
    return this == ScreenState.Loading
}

fun ScreenState.isError(): Boolean {
    return this is ScreenState.ErrorPlaceHolder || this is ScreenState.ErrorFullScreen
}