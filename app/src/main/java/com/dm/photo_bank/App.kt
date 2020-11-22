package com.dm.photo_bank

import android.app.Application
import com.dm.photo_bank.di.appModule
import com.dm.photo_bank.di.dataModule
import com.dm.photo_bank.di.netWorkModule
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(
                    appModule,
                    netWorkModule,
                    dataModule
                    )
            )
            applicationContext
        }
    }
}