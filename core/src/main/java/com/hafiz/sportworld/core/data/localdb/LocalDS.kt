package com.hafiz.sportworld.core.data.localdb


import androidx.lifecycle.LiveData
import com.hafiz.sportworld.core.data.localdb.entities.SportWorldEntities
import com.hafiz.sportworld.core.data.localdb.room.SportWorldDao
import io.reactivex.Flowable

class LocalDS(private val sportDao: SportWorldDao) {

    fun getAllSport(): Flowable<List<SportWorldEntities>> = sportDao.getSportWorld()
    fun getFavoriteSport(): LiveData<List<SportWorldEntities>> = sportDao.getFavSport()
    fun insertSportType(sportList: List<SportWorldEntities>) = sportDao.insertSportType(sportList)
    fun setSportFav(sport: SportWorldEntities, newState: Boolean) {
        sport.isFavorite = newState
        sportDao.favSportUpdate(sport)
    }
}