package com.hafiz.sportworld.detail

import androidx.lifecycle.ViewModel
import com.hafiz.sportworld.core.domain.model.SportWold
import com.hafiz.sportworld.core.domain.usecase.SportUseCase

class DetailSportViewModel(private val sportUseCase: SportUseCase) : ViewModel() {
    fun setFavSport(sport: SportWold, newStatus: Boolean)=sportUseCase.setFavoriteSportWold(sport,newStatus)
}