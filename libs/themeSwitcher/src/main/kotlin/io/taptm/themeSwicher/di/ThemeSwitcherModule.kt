package io.taptm.themeSwicher.di

import io.taptm.themeSwicher.ThemeVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun themeSwitcherModule() = module {
    viewModelOf(::ThemeVM)
}