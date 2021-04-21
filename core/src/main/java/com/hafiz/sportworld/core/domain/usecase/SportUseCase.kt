package com.hafiz.sportworld.core.domain.usecase

import androidx.lifecycle.LiveData
import com.hafiz.sportworld.core.data.Resource
import com.hafiz.sportworld.core.domain.model.SportWold
import io.reactivex.Flowable

interface SportUseCase {
    fun setFavoriteSportWold(sport: SportWold, state: Boolean)
    fun getSportWoldList(): Flowable<Resource<List<SportWold>>>
    fun getSportWoldFavorite(): LiveData<List<SportWold>>
}