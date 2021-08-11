package com.test.argenttestproject.robertpapp

import android.app.Application
import com.test.argenttestproject.robertpapp.data.SharedPreferenceRepository
import com.test.argenttestproject.robertpapp.di.appModule
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatformTools
import rxdogtag2.RxDogTag
import timber.log.Timber

class MainApplication : Application() {

    private val sharedPreferenceRepository by lazy {
        KoinPlatformTools.defaultContext().get().get<SharedPreferenceRepository>()
    }


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }

        RxDogTag.install()
        RxJavaPlugins.setErrorHandler(Timber::e)

        sharedPreferenceRepository.setWalletAddress(
            "0xde57844f758a0a6a1910a4787ab2f7121c8978c3"
        ).subscribeBy(
            onSuccess = {

            },
            onError = {

            }
        )
    }
}
