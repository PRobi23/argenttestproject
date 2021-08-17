package com.test.argenttestproject.robertpapp.data.local.sharedRepository

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.Single

class SharedPreferenceRepositoryImpl(
    private val sharedPreference: SharedPreferences,
    private val sharedPreferenceEditor: SharedPreferences.Editor
) :
    SharedPreferenceRepository {

    override fun getWalletAddress() =
        Single.just(WALLET_ADDRESS.getString())

    override fun setWalletAddress(value: String): Single<Boolean> =
        Single.just(WALLET_ADDRESS.put(value))

    override fun getApiKey(): Single<String> = Single.just(API_KEY.getString())

    override fun setApiKey(value: String): Single<Boolean> = Single.just(API_KEY.put(value))

    companion object {
        const val WALLET_ADDRESS = "WALLET_ADDRESS"
        const val API_KEY = "APIKEY"
    }

    private fun String.put(string: String): Boolean {
        sharedPreferenceEditor.putString(this, string)
        return sharedPreferenceEditor.commit()
    }

    private fun String.getString() = sharedPreference.getString(this, "")!!
}
