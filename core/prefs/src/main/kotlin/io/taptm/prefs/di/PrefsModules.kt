package io.taptm.prefs.di

import io.taptm.prefs.repos.PrefsProvider
import io.taptm.prefs.repos.PrefsProviderImpl
import io.taptm.prefs.repos.PrefsRepository
import io.taptm.prefs.repos.PrefsRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun prefsModules() = module {

    singleOf(::PrefsProviderImpl) { bind<PrefsProvider>() }
    singleOf(::PrefsRepositoryImpl) { bind<PrefsRepository>() }

}