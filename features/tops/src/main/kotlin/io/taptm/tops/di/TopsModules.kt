package io.taptm.tops.di

import io.taptm.tops.screens.animeTop.mapper.AnimeTopMapper
import io.taptm.tops.screens.animeTop.presenter.AnimeTopVM
import io.taptm.tops.screens.animeTop.repo.AnimeTopRepo
import io.taptm.tops.screens.animeTop.repo.AnimeTopRepoImpl
import io.taptm.tops.screens.details.mapper.DetailsMapper
import io.taptm.tops.screens.details.presentation.DetailsVM
import io.taptm.tops.screens.details.repo.DetailsRepo
import io.taptm.tops.screens.details.repo.DetailsRepoImpl
import io.taptm.tops.screens.mangaTop.mapper.MangaTopMapper
import io.taptm.tops.screens.mangaTop.presenter.MangaTopVM
import io.taptm.tops.screens.mangaTop.repo.MangaTopRepo
import io.taptm.tops.screens.mangaTop.repo.MangaTopRepoImpl
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun topsModule() = module {

    // ViewModels
    viewModelOf(::AnimeTopVM)
    viewModelOf(::MangaTopVM)
    viewModelOf(::DetailsVM)

    // Repos
    singleOf(::AnimeTopRepoImpl) bind AnimeTopRepo::class
    singleOf(::MangaTopRepoImpl) bind MangaTopRepo::class
    singleOf(::DetailsRepoImpl) bind DetailsRepo::class

    // Mappers
    single { AnimeTopMapper() }
    single { MangaTopMapper() }
    single { DetailsMapper() }
}