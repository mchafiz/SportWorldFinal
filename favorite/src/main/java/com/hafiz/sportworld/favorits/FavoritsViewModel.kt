package com.hafiz.sportworld.favorits

import androidx.lifecycle.ViewModel
import com.hafiz.sportworld.core.domain.usecase.SportUseCase

class FavoritsViewModel(sportUseCase: SportUseCase) : ViewModel() {
    val favoriteSport =  sportUseCase.getSportWoldFavorite()
}
