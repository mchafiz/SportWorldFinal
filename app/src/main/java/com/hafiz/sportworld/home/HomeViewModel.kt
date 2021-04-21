package com.hafiz.sportworld.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.hafiz.sportworld.core.domain.usecase.SportUseCase

class HomeViewModel(sportUseCase: SportUseCase) : ViewModel() {
    val sport = LiveDataReactiveStreams.fromPublisher(sportUseCase.getSportWoldList())
}