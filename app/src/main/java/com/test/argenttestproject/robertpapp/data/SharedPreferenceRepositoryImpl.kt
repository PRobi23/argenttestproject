package com.test.argenttestproject.robertpapp.data

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.Single

class SharedPreferenceRepositoryImpl(
    private val sharedPreference: SharedPreferences,
    private val sharedPreferenceEditor: SharedPreferences.Editor
) :
    SharedPreferenceRepository {

    private fun String.put(string: String): Boolean {
        sharedPreferenceEditor.putString(this, string)
        return sharedPreferenceEditor.commit()
    }

    private fun String.getString() = sharedPreference.getString(this, "")!!

    override fun getWalletAddress() =
        Single.just(WALLET_ADDRESS.getString())

    override fun setWalletAddress(value: String): Single<Boolean> =
        Single.just(WALLET_ADDRESS.put(value))

    companion object {
        const val WALLET_ADDRESS = "WALLET_ADDRESS"
    }

}
