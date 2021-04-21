package com.hafiz.sportworld.core.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hafiz.sportworld.core.data.localdb.LocalDS
import com.hafiz.sportworld.core.data.remotedb.RemoteDataSource
import com.hafiz.sportworld.core.data.remotedb.internet.ResponseAPI
import com.hafiz.sportworld.core.data.remotedb.respon.ResponseSport
import com.hafiz.sportworld.core.domain.model.SportWold
import com.hafiz.sportworld.core.domain.repository.ISportRepository
import com.hafiz.sportworld.core.utils.AppExecutors
import com.hafiz.sportworld.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SportWorldRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDS,
    private val appExecutors: AppExecutors
) : ISportRepository {
    override fun setFavoriteSportWold(sport: SportWold, state: Boolean) {
        val sportEntities = DataMapper.mapDomainToEntity(sport)
        appExecutors.diskIO().execute { localDataSource.setSportFav(sportEntities, state)
    }}

    override fun getSportWoldList(): Flowable<Resource<List<SportWold>>> =
        object : NetworkBoundResource<List<SportWold>, List<ResponseSport>>(appExecutors) {
        override fun loadFromDB(): Flowable<List<SportWold>> {
            return localDataSource.getAllSport().map { DataMapper.mapEntitiesToDomain(it) }
        }
        override fun shouldFetch(data: List<SportWold>?): Boolean = true
        override fun createCall(): Flowable<ResponseAPI<List<ResponseSport>>> = remoteDataSource.getAllSport()
        override fun saveCallResult(data: List<ResponseSport>) {
            val sportList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertSportType(sportList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }.asFlowable()

    override fun getSportWoldFavorite(): LiveData<List<SportWold>> {


        return Transformations.map(localDataSource.getFavoriteSport()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }
}