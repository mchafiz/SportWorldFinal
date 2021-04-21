package com.hafiz.sportworld.core.domain.usecase


import com.hafiz.sportworld.core.domain.model.SportWold
import com.hafiz.sportworld.core.domain.repository.ISportRepository

class SportInteractor(private val sportRepository: ISportRepository): SportUseCase {
    override fun setFavoriteSportWold(sport: SportWold, state: Boolean) = sportRepository.setFavoriteSportWold(sport,state)
    override fun getSportWoldList() = sportRepository.getSportWoldList()
    override fun getSportWoldFavorite()= sportRepository.getSportWoldFavorite()
}
