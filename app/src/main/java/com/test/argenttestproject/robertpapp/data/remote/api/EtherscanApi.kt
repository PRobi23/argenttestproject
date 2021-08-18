package com.test.argenttestproject.robertpapp.data.remote.api

import com.test.argenttestproject.robertpapp.data.remote.response.etherscan.EtherscanResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EtherscanApi {

    @GET("api")
    fun getHistoricalBalance(
        @Query("module") module: String = "account",
        @Query("action") action: String = "tokenbalance",
        @Query("contractaddress") contractaddress: String,
        @Query("address") address: String,
        @Query("tag") tag: String = "latest",
        @Query("apikey") apikey: String
    ): Single<EtherscanResult>
}
