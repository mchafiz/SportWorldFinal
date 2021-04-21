@file:Suppress("unused")

package com.hafiz.sportworld

import android.app.Application

import com.hafiz.sportworld.core.depedencyinject.databaseModule
import com.hafiz.sportworld.core.depedencyinject.networkModule
import com.hafiz.sportworld.core.depedencyinject.repositoryModule
import com.hafiz.sportworld.di.useCaseModule
import com.hafiz.sportworld.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MySportApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MySportApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}