package com.hafiz.sportworld.favorits.di

import com.hafiz.sportworld.favorits.FavoritsViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val favoritsModule = module {
    viewModel { FavoritsViewModel(get()) }
}