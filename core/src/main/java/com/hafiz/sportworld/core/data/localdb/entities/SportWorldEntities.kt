package com.hafiz.sportworld.core.data.localdb.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sportable")
data class SportWorldEntities(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idSport")
    var idSport: String,

    @ColumnInfo(name = "strSport")
    var strSport: String,

    @ColumnInfo(name = "strFormat")
    var strFormat: String,

    @ColumnInfo(name = "strSportThumb")
    var strSportThumb: String,

    @ColumnInfo(name = "strSportDescription")
    var strSportDescription: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
