package io.taptm.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerError(
    @SerialName("error")
    val error: String,
    @SerialName("message")
    val message: String,
    @SerialName("report_url")
    val reportUrl: String,
    @SerialName("status")
    val status: Int,
    @SerialName("type")
    val type: String
)


