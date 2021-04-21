package com.hafiz.sportworld.core.data.remotedb.internet

import com.hafiz.sportworld.core.data.remotedb.respon.ResponseSportList
import io.reactivex.Flowable
import retrofit2.http.GET

interface ServiceAPI {
    @GET("all_sports.php")
    fun getList(): Flowable<ResponseSportList>
}
