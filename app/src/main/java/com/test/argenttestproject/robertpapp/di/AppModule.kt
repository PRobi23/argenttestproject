package com.test.argenttestproject.robertpapp.di

import android.app.Application
import android.content.SharedPreferences
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepository
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepositoryImpl
import com.test.argenttestproject.robertpapp.data.local.sharedRepository.SharedPreferenceRepository
import com.test.argenttestproject.robertpapp.data.local.sharedRepository.SharedPreferenceRepositoryImpl
import com.test.argenttestproject.robertpapp.ui.MainViewModelImpl
import com.test.argenttestproject.robertpapp.ui.introScreen.IntroScreenViewModelImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { IntroScreenViewModelImpl(get()) }

    viewModel { MainViewModelImpl(get(), get()) }

    single {
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }

    single<SharedPreferenceRepository> {
        SharedPreferenceRepositoryImpl(get(), get())
    }

    single<EthplorerTokenRepository> {
        EthplorerTokenRepositoryImpl(get(), get())
    }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences(
        "argent-test",
        android.content.Context.MODE_PRIVATE
    )
}
