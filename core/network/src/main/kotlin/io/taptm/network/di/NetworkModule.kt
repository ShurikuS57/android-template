package io.taptm.network.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.taptm.common.Flavor
import io.taptm.network.ApiService
import io.taptm.network.httpClientAndroid
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providersApiService(httpClient: HttpClient) = ApiService(httpClient)

    @Provides
    @Singleton
    fun httpClientProvider(
        @ApplicationContext context: Context,
        @Named("URL") url: String,
        @Named("FLAVOR") flavorStr: String
    ): HttpClient {
        val flavor = Flavor.parse(flavorStr)
        return httpClientAndroid(context, serverUrl = url, flavor = flavor)
    }
}