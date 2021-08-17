package com.test.argenttestproject.robertpapp.di

import com.test.argenttestproject.BuildConfig
import com.test.argenttestproject.robertpapp.data.remote.api.EtherscanApi
import com.test.argenttestproject.robertpapp.data.remote.api.EthplorerApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideEthplorerApi(get()) }
    single { provideEtherscanApi(get()) }
}

fun provideEthplorerApi(okHttpClient: OkHttpClient): EthplorerApi =
    Retrofit.Builder()
        .baseUrl(BuildConfig.ETHPLORER_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(EthplorerApi::class.java)

fun provideEtherscanApi(okHttpClient: OkHttpClient): EtherscanApi =
    Retrofit.Builder()
        .baseUrl(BuildConfig.ETHERSCAN_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(EtherscanApi::class.java)

fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()
