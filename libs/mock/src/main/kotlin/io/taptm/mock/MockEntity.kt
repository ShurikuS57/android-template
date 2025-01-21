package io.taptm.mock

import io.ktor.http.HttpMethod

data class MockEntity(
    val method: HttpMethod,
    val endWith: String,
    val jsonObject: String,
)