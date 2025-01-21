package io.taptm.network.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.taptm.common.models.ErrorEntity
import io.taptm.common.models.ErrorType
import io.taptm.network.models.RepoResult
import io.taptm.network.models.ServerError
import org.koin.core.component.KoinComponent
import java.net.SocketTimeoutException

object ThrowableHandler : KoinComponent {

    suspend fun catchError(response: HttpResponse): ErrorEntity {
        val message =
            "Error executing service call: ${response.request.method.value} ${response.request.url} (${response.status})"
        return when (response.status.value) {
            500 -> catchServerError(response)
            else -> ErrorEntity(
                errorType = ErrorType.Unknown,
                title = "Error",
                errorCode = response.status.value.toString(),
                message = message
            )
        }
    }

    fun network(throwable: Throwable): RepoResult.Error {
        return when (throwable) {
            is SocketTimeoutException -> RepoResult.Error(
                ErrorEntity(
                    title = "Error",
                    message = "SocketTimeoutException",
                    errorType = ErrorType.Unknown
                )
            )

            else -> {
                RepoResult.Error(
                    ErrorEntity(
                        errorType = ErrorType.Unknown,
                        title = "Error",
                        message = throwable.message.toString()
                    )
                )
            }
        }
    }

    private suspend fun catchServerError(response: HttpResponse): ErrorEntity {
        return try {
            val serverError = response.body<ServerError>()
            val title = serverError.error
            val message = serverError.message
            ErrorEntity(
                errorType = ErrorType.Server,
                errorCode = serverError.status.toString(),
                title = title,
                message = message
            )
        } catch (_: Exception) {
            ErrorEntity(
                errorType = ErrorType.Unknown,
            )
        }
    }

}