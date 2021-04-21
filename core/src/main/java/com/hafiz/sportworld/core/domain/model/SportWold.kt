package com.hafiz.sportworld.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SportWold(
    val idSport: String,
    val strSport: String,
    val strFormat: String,
    val strSportThumb: String,
    val strSportDescription: String,
    val isFavorite: Boolean
) : Parcelable