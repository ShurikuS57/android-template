package io.taptm.network

import android.content.Context
import android.util.Log
import com.pluto.plugins.network.ktor.PlutoKtorInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.plugin
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.taptm.common.Flavor
import io.taptm.mock.MockUtils.withMock
import kotlinx.serialization.json.Json

private const val NETWORK_REQUEST_TIME_OUT = 30_000L
private const val NETWORK_CONNECT_TIME_OUT = 15_000L

internal fun httpClientAndroid(context: Context, serverUrl: String, flavor: Flavor) = HttpClient(Android) {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                useAlternativeNames = true
                ignoreUnknownKeys = true
                encodeDefaults = false
            }
        )
    }

    install(HttpTimeout) {
        requestTimeoutMillis = NETWORK_REQUEST_TIME_OUT
        connectTimeoutMillis = NETWORK_CONNECT_TIME_OUT
        socketTimeoutMillis = NETWORK_REQUEST_TIME_OUT
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>", message)
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }

    install(PlutoKtorInterceptor)

    defaultRequest {
        url(serverUrl)
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
    }

}.apply {
    if (flavor == Flavor.DEV) {
        plugin(HttpSend).intercept { request ->
            withMock(
                request,
                HttpMockData.getMockList(context)
            )
        }
    }
}
