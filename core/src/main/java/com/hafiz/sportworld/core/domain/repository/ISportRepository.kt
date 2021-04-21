package com.hafiz.sportworld.core.domain.repository


import androidx.lifecycle.LiveData
import com.hafiz.sportworld.core.data.Resource
import com.hafiz.sportworld.core.domain.model.SportWold
import io.reactivex.Flowable

interface ISportRepository {
    fun setFavoriteSportWold(sport: SportWold, state: Boolean)
    fun getSportWoldList(): Flowable<Resource<List<SportWold>>>
    fun getSportWoldFavorite(): LiveData<List<SportWold>>
}
