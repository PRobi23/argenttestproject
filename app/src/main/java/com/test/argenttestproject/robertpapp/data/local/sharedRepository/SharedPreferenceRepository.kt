package com.test.argenttestproject.robertpapp.data.local.sharedRepository

import io.reactivex.rxjava3.core.Single

interface SharedPreferenceRepository {

    fun setWalletAddress(value: String) : Single<Boolean>

    fun getWalletAddress(): Single<String>

    fun setApiKey(value: String) : Single<Boolean>

    fun getApiKey(): Single<String>
}
