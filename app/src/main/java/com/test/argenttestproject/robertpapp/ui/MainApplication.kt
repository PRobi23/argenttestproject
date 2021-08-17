package com.test.argenttestproject.robertpapp.ui

import android.app.Application
import com.test.argenttestproject.robertpapp.di.appModule
import com.test.argenttestproject.robertpapp.di.databaseModule
import com.test.argenttestproject.robertpapp.di.networkModule
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import rxdogtag2.RxDogTag
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule, networkModule, databaseModule)
        }

        RxDogTag.install()
        RxJavaPlugins.setErrorHandler(Timber::e)
    }
}
