package com.test.argenttestproject.robertpapp.data.remote.api

import com.test.argenttestproject.robertpapp.data.remote.response.EthplorerToken
import com.test.argenttestproject.robertpapp.data.remote.response.EthplorerTokens
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EthplorerApi {

    @GET("getTopTokens")
    fun getTopTokens(
        @Query("limit") limit: Int = 100,
        @Query("apiKey") apiKey: String = "freekey"
    ): Single<EthplorerTokens>
}
