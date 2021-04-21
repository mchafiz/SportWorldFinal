package com.hafiz.sportworld.core.data.localdb.room


import androidx.lifecycle.LiveData
import androidx.room.*
import com.hafiz.sportworld.core.data.localdb.entities.SportWorldEntities
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface SportWorldDao {
    @Update
    fun favSportUpdate(sportWorldEntities: SportWorldEntities)

    @Query("SELECT * FROM sportable")
    fun getSportWorld(): Flowable<List<SportWorldEntities>>

    @Query("SELECT * FROM sportable where isFavorite = 1")
    fun getFavSport(): LiveData<List<SportWorldEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSportType(sport: List<SportWorldEntities>) : Completable


}