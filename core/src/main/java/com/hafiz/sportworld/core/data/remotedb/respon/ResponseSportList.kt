package com.hafiz.sportworld.core.data.remotedb.respon

import com.google.gson.annotations.SerializedName

data class ResponseSportList(
    @field:SerializedName("sports")
    val sports: List<ResponseSport>,

    )