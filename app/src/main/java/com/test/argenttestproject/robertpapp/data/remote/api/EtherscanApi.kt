package com.test.argenttestproject.robertpapp.data.remote.api

import com.test.argenttestproject.robertpapp.data.remote.response.ethplorer.EthplorerTokens
import com.test.argenttestproject.robertpapp.ui.ercTwentyScreen.TokenValueResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EtherscanApi {

    @GET("api")
    fun getHistoricalBalance(
        @Query("module") module: String = "account",
        @Query("action") action: String = "tokenbalance",
        @Query("action") contractaddress: String,
        @Query("action") address: String,
        @Query("action") tag: String = "latest",
        @Query("action") apikey: String
    ): Single<TokenValueResult>
}
