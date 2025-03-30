package io.taptm.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.taptm.sample.BuildConfig
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @Singleton
    @Named("URL")
    fun provideUrl() : String {
        return BuildConfig.URL
    }

    @Provides
    @Singleton
    @Named("FLAVOR")
    fun provideFlavors() : String {
        return BuildConfig.FLAVOR
    }

}