package io.taptm.network.utils

import android.content.Context
import io.taptm.network.mapper.Mapper
import kotlinx.serialization.json.Json
import java.io.FileNotFoundException
import java.io.IOException

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

fun Context.loadAssetToString(filePath: String) = try {
    this.assets.open(filePath).bufferedReader().use { it.readText() }
} catch (e: IOException) {
    throw FileNotFoundException(e.message)
}
