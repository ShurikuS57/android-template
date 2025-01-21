package io.taptm.network.di

import android.content.Context
import io.ktor.client.HttpClient
import io.taptm.common.Flavor
import io.taptm.network.ApiService
import io.taptm.network.httpClientAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin

val networkModule = module {
    single { provideApiService(get()) }
    single { provideHttpClient(androidContext()) }
}

fun provideHttpClient(context: Context): HttpClient {
    val url = getKoin().getProperty<String>("URL").orEmpty()
    val flavor = Flavor.parse(getKoin().getProperty("FLAVOR"))
    return httpClientAndroid(context = context, serverUrl = url, flavor = flavor)
}

fun provideApiService(httpClient: HttpClient): ApiService {
    return ApiService(httpClient)
}