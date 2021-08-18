package com.test.argenttestproject.robertpapp.data.remote.response.etherscan

import com.test.argenttestproject.robertpapp.data.local.sharedRepository.SharedPreferenceRepository
import com.test.argenttestproject.robertpapp.data.remote.api.EtherscanApi
import com.test.argenttestproject.robertpapp.ui.ercTwentyScreen.TokenValueResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.Singles

class EtherscanRepositoryImpl(
    private val etherscanApi: EtherscanApi,
    private val sharedPreferenceRepository: SharedPreferenceRepository
) : EtherscanRepository {


    override fun getHistoricalBalance(contactAddress: String): Single<EtherscanResult> =
        Singles.zip(
            sharedPreferenceRepository.getApiKey(),
            sharedPreferenceRepository.getWalletAddress()
        ).flatMap { apiKeyWithWalletAddress ->
            val apiKey = apiKeyWithWalletAddress.first
            val walletAddress = apiKeyWithWalletAddress.second

            etherscanApi.getHistoricalBalance(
                contractaddress = contactAddress,
                address = walletAddress,
                apikey = apiKey
            )
        }

}
