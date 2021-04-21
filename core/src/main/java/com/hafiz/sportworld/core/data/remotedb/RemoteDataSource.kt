package com.hafiz.sportworld.core.data.remotedb

import android.annotation.SuppressLint
import android.util.Log

import com.hafiz.sportworld.core.data.remotedb.internet.ResponseAPI
import com.hafiz.sportworld.core.data.remotedb.internet.ServiceAPI
import com.hafiz.sportworld.core.data.remotedb.respon.ResponseSport
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject



class RemoteDataSource(private val apiService: ServiceAPI) {
    @SuppressLint("CheckResult")
    fun getAllSport(): Flowable<ResponseAPI<List<ResponseSport>>> {
        val resultData = PublishSubject.create<ResponseAPI<List<ResponseSport>>>()
        val user = apiService.getList()
        user
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.sports
                resultData.onNext(if (dataArray.isNotEmpty()) ResponseAPI.Success(dataArray) else ResponseAPI.Empty)
            }, { error ->
                resultData.onNext(ResponseAPI.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}

