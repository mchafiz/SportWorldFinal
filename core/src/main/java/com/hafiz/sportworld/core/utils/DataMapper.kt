package com.hafiz.sportworld.core.utils


import com.hafiz.sportworld.core.data.localdb.entities.SportWorldEntities
import com.hafiz.sportworld.core.data.remotedb.respon.ResponseSport
import com.hafiz.sportworld.core.domain.model.SportWold


object DataMapper {

    fun mapResponsesToEntities(input: List<ResponseSport>): List<SportWorldEntities> {
        val sportList = ArrayList<SportWorldEntities>()
        input.map {
            val sport = SportWorldEntities(
                idSport = it.idSport,
                strSport = it.strSport,
                strFormat = it.strFormat,
                strSportThumb = it.strSportThumb,
                strSportDescription = it.strSportDescription,
                isFavorite = false
            )
            sportList.add(sport)
        }
        return sportList
    }

    fun mapEntitiesToDomain(input: List<SportWorldEntities>): List<SportWold> =
        input.map {
            SportWold(
                idSport = it.idSport,
                strSport = it.strSport,
                strFormat = it.strFormat,
                strSportThumb = it.strSportThumb,
                strSportDescription = it.strSportDescription,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: SportWold) = SportWorldEntities(
        idSport = input.idSport,
        strSport = input.strSport,
        strFormat = input.strFormat,
        strSportThumb = input.strSportThumb,
        strSportDescription = input.strSportDescription,
        isFavorite = input.isFavorite,
    )
}
