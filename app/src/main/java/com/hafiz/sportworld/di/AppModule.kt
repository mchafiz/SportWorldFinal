package com.hafiz.sportworld.di

import com.hafiz.sportworld.core.domain.usecase.SportInteractor
import com.hafiz.sportworld.core.domain.usecase.SportUseCase
import com.hafiz.sportworld.detail.DetailSportViewModel
import com.hafiz.sportworld.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SportUseCase> { SportInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailSportViewModel(get()) }
}