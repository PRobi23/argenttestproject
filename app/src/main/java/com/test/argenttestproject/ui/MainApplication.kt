package com.test.argenttestproject.ui

import android.app.Application
import com.test.argenttestproject.ui.di.appModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //androidLogger()
            //androidContext()
            modules(appModule)
        }
    }
}
