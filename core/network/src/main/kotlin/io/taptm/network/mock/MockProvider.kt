package io.taptm.network.mock

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.AttributeKey
import io.taptm.network.utils.loadAssetToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import timber.log.Timber

private const val MOCK_CONFIG_ASSET = "mock/mock_config.json"
private const val MOCK_DEFAULT_STATUS = 200
private const val TAG = "MockProvider"

/**
 * Matches outgoing requests against [MOCK_CONFIG_ASSET] and returns the
 * corresponding JSON asset instead of performing a real network call.
 */
internal object MockProvider {

    private val jsonFormat = Json { ignoreUnknownKeys = true }

    @Volatile
    private var cachedRoutes: List<MockRouteConfig>? = null

    private val mockResponseKey = AttributeKey<MockResponse>(TAG)

    private val mockEngine = MockEngine { request ->
        val response = request.attributes[mockResponseKey]
        respond(
            content = response.body,
            status = HttpStatusCode.fromValue(response.status),
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        )
    }

    private val mockClient = HttpClient(mockEngine) {
        install(ContentNegotiation) {
            json(jsonFormat)
        }
    }

    suspend fun intercept(
        context: Context,
        request: HttpRequestBuilder,
        execute: suspend (HttpRequestBuilder) -> HttpClientCall
    ): HttpClientCall {
        if (!MockSettings.isEnabled) {
            return execute(request)
        }

        val route = routes(context).firstOrNull { it.matches(request) } ?: return execute(request)
        val body = runCatching { context.loadAssetToString(route.asset) }.getOrElse { error ->
            Timber.tag(TAG).e(error, "Mock asset '${route.asset}' can not be read")
            return execute(request)
        }

        Timber.tag(TAG).d(
            "Mock ${request.method.value} ${request.url.build().encodedPath} -> ${route.asset}"
        )

        val response = mockClient.request(request.url.toString()) {
            method = request.method
            attributes.put(mockResponseKey, MockResponse(route.status, body))
        }
        return response.call
    }

    private fun routes(context: Context): List<MockRouteConfig> {
        return cachedRoutes ?: synchronized(this) {
            cachedRoutes ?: loadRoutes(context).also { cachedRoutes = it }
        }
    }

    private fun loadRoutes(context: Context): List<MockRouteConfig> {
        return runCatching {
            val rawConfig = context.loadAssetToString(MOCK_CONFIG_ASSET)
            jsonFormat.decodeFromString<MockConfig>(rawConfig).routes
        }.onFailure { error ->
            Timber.tag(TAG).w(error, "Mock config '$MOCK_CONFIG_ASSET' can not be read")
        }.getOrDefault(emptyList())
    }
}

private data class MockResponse(
    val status: Int,
    val body: String
)

@Serializable
private data class MockConfig(
    val routes: List<MockRouteConfig> = emptyList()
)

@Serializable
private data class MockRouteConfig(
    val method: String = HttpMethod.Get.value,
    val path: String? = null,
    val pathRegex: String? = null,
    val asset: String,
    val status: Int = MOCK_DEFAULT_STATUS
) {

    fun matches(request: HttpRequestBuilder): Boolean {
        if (!request.method.value.equals(method, ignoreCase = true)) {
            return false
        }

        val requestPath = request.url.build().encodedPath.trim('/')
        return when {
            pathRegex != null -> Regex(pathRegex).matches(requestPath)
            path != null -> path.trim('/') == requestPath
            else -> false
        }
    }
}
