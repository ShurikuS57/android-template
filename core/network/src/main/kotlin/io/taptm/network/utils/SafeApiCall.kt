package io.taptm.network.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.taptm.network.mapper.Mapper
import io.taptm.network.models.RepoResult

suspend inline fun <reified T : Any> safeApiCall(
    call: () -> HttpResponse,
): RepoResult<T> {
    return try {
        val response = call()
        if (response.status.isSuccess()) {
            val data = response.body<T>()
            RepoResult.Success(data)
        } else {
            RepoResult.Error(ThrowableHandler.catchError(response))
        }
    } catch (error: Throwable) {
        return ThrowableHandler.network(error)
    }
}

suspend inline fun <reified T : Any, R : Any> safeApiCall(
    mapper: Mapper<T, R>,
    call: () -> HttpResponse,
): RepoResult<R> {
    return try {
        when (val result = safeApiCall<T>(call)) {
            is RepoResult.Success -> {
                RepoResult.Success(mapper.toDomain(result.data))
            }

            is RepoResult.Error -> {
                RepoResult.Error(result.error)
            }
        }
    } catch (error: Throwable) {
        ThrowableHandler.network(error)
    }
}