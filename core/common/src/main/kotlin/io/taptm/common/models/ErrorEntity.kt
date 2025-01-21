package io.taptm.common.models

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Keep
@Serializable
@Parcelize
data class ErrorEntity(
    val errorType: ErrorType,
    val errorCode: String = "",
    val title: String = "",
    val message: String = "",
    val button: String = ""
) : Parcelable {

    fun toJson(): String {
        return Uri.encode(Json.encodeToString(this))
    }

    companion object {
        fun fromJson(json: String): ErrorEntity {
            val decode = Uri.decode(json)
            return Json.decodeFromString<ErrorEntity>(decode)
        }
    }
}

@Keep
@Serializable
@Parcelize
sealed class ErrorType : Parcelable {
    @Serializable
    data object Server : ErrorType()

    @Serializable
    data object Connection : ErrorType()

    @Serializable
    data object Unknown : ErrorType()
}