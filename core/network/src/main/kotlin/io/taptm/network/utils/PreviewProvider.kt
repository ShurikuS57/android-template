package io.taptm.network.utils

import android.content.Context
import io.taptm.mock.loadAssetToString
import io.taptm.network.mapper.Mapper
import kotlinx.serialization.json.Json

object PreviewProvider {

    inline fun <reified T : Any, A : Any> getData(context: Context, jsonFilePath: String, mapper: Mapper<T, A>): A {
        val jsonStr = context.loadAssetToString(jsonFilePath)
        val json = Json { ignoreUnknownKeys = true }
        val obj = json.decodeFromString<T>(jsonStr)
        return mapper.toDomain(obj)
    }

    inline fun <reified T : Any, A : Any> getData(jsonString: String, mapper: Mapper<T, A>): A {
        val json = Json { ignoreUnknownKeys = true }
        val obj = json.decodeFromString<T>(jsonString)
        return mapper.toDomain(obj)
    }
}

