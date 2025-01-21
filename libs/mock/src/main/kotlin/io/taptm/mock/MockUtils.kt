package io.taptm.mock

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.Sender
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.request
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.FileNotFoundException
import java.io.IOException

object MockUtils {

    @Throws(IllegalStateException::class)
    suspend inline fun Sender.withMock(
        request: HttpRequestBuilder,
        mockData: List<MockEntity>
    ): HttpClientCall {
        val url = request.url.toString()
        val filterMocks =
            mockData.filter { item -> item.method == request.method && url.endsWith(item.endWith) }
        return if (filterMocks.isEmpty()) {
            execute(request)
        } else if (filterMocks.size == 1) {
            val item = filterMocks[0]
            when (request.method) {
                HttpMethod.Get -> {
                    val client = getMockClient(item.jsonObject)
                    client.request(HttpRequestBuilder()).call
                }

                else -> execute(request)
            }
        } else {
            throw IllegalStateException("Mock data size more 1")
        }
    }

    fun getMockClient(
        mockJson: String
    ): HttpClient {

        val mockEngine = MockEngine { _ ->
            respondOk(mockJson)
        }
        val client = HttpClient(mockEngine) {
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
        }
        return client
    }

    private fun MockRequestHandleScope.respondOk(data: String): HttpResponseData =
        respond(
            status = HttpStatusCode.OK,
            headers = headersOf(name = HttpHeaders.ContentType, value = "application/json"),
            content = data
        )

    inline fun <reified T> getObjectJson(context: Context, resourceFilename: String): String {
        val jsonString = context.loadAssetToString(resourceFilename)
        val json = Json { ignoreUnknownKeys = true }
        val dataClass = json.decodeFromString<T>(jsonString)
        return Json.encodeToString(serializer = serializer(), value = dataClass)
    }
}

fun Context.loadAssetToString(filePath: String) = try {
    this.assets.open(filePath).bufferedReader().use { it.readText() }
} catch (e: IOException) {
    throw FileNotFoundException(e.message)
}