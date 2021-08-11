package com.test.argenttestproject.robertpapp.data

import io.reactivex.rxjava3.core.Single

interface SharedPreferenceRepository {

    fun setWalletAddress(value: String) : Single<Boolean>

    fun getWalletAddress(): Single<String>
}
