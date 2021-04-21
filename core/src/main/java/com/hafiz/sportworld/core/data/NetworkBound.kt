package com.hafiz.sportworld.core.data


import com.hafiz.sportworld.core.data.remotedb.internet.ResponseAPI
import com.hafiz.sportworld.core.utils.AppExecutors
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {
    private val result = PublishSubject.create<Resource<ResultType>>()
    private val mCompositeDisposable = CompositeDisposable()


    init {
        @Suppress("LeakingThis")
        val dbSource = loadFromDB()
        val db = dbSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { value ->
                dbSource.unsubscribeOn(Schedulers.io())
                if (shouldFetch(value)) {
                    fetchFromNetwork()
                } else {
                    result.onNext(Resource.Berhasil(value))
                }
            }
        mCompositeDisposable.add(db)
    }


    private fun fetchFromNetwork() {

        val apiResponse = createCall()

        result.onNext(Resource.Waiting(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe { response ->
                when (response) {
                    is ResponseAPI.Success -> {
                        saveCallResult(response.data)
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(Resource.Berhasil(it))
                            }
                    }
                    is ResponseAPI.Empty -> {
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(Resource.Berhasil(it))
                            }
                    }
                    is ResponseAPI.Error -> {
                        onFetchFailed()
                        result.onNext(Resource.Exception(response.errorMessage, null))
                    }
                }
            }
        mCompositeDisposable.add(response)
    }
    protected abstract fun saveCallResult(data: RequestType)
    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB(): Flowable<ResultType>
    protected abstract fun createCall(): Flowable<ResponseAPI<RequestType>>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    fun asFlowable(): Flowable<Resource<ResultType>> =
        result.toFlowable(BackpressureStrategy.BUFFER)

}