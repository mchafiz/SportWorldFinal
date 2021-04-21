package com.hafiz.sportworld.core.depedencyinject

import androidx.room.Room
import com.hafiz.sportworld.core.data.SportWorldRepository
import com.hafiz.sportworld.core.data.localdb.LocalDS
import com.hafiz.sportworld.core.data.localdb.room.SportWorldDB
import com.hafiz.sportworld.core.data.remotedb.RemoteDataSource
import com.hafiz.sportworld.core.data.remotedb.internet.ServiceAPI
import com.hafiz.sportworld.core.domain.repository.ISportRepository
import com.hafiz.sportworld.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<SportWorldDB>().sportWorldDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("sportworld".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            SportWorldDB::class.java, "SportWold.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostName = "www.thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/ctt1haazs8U6LJbBhG1dMDCxflw6Q5LRFqlJP+iCf3E=")
            .add(hostName, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostName, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ServiceAPI::class.java)
    }
}

val repositoryModule = module {
    single { LocalDS(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ISportRepository> { SportWorldRepository(get(), get(), get()) }
}