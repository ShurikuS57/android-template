package io.taptm.tops.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.taptm.tops.screens.animeTop.repo.AnimeTopRepo
import io.taptm.tops.screens.animeTop.repo.AnimeTopRepoImpl
import io.taptm.tops.screens.details.repo.DetailsRepo
import io.taptm.tops.screens.details.repo.DetailsRepoImpl
import io.taptm.tops.screens.mangaTop.repo.MangaTopRepo
import io.taptm.tops.screens.mangaTop.repo.MangaTopRepoImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class TopModule {

    @Binds
    @Singleton
    internal abstract fun bindAnimeTopRepo(imp: AnimeTopRepoImpl): AnimeTopRepo


    @Binds
    @Singleton
    internal abstract fun bindDetailsRepo(imp: DetailsRepoImpl): DetailsRepo


    @Binds
    @Singleton
    internal abstract fun bindMangaRepo(imp: MangaTopRepoImpl): MangaTopRepo
}