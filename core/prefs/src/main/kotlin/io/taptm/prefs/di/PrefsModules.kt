package io.taptm.prefs.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.taptm.prefs.repos.PrefsProvider
import io.taptm.prefs.repos.PrefsProviderImpl
import io.taptm.prefs.repos.PrefsRepository
import io.taptm.prefs.repos.PrefsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrefsModule {

    @Binds
    @Singleton
    internal abstract fun prefsProviderBind(impl: PrefsProviderImpl) : PrefsProvider


    @Binds
    @Singleton
    internal abstract fun bindPrefsRepo(impl: PrefsRepositoryImpl) : PrefsRepository
}