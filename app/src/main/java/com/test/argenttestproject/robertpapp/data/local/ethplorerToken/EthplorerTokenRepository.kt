package com.test.argenttestproject.robertpapp.data.local.ethplorerToken

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface EthplorerTokenRepository {

    fun updateEthplorerTokens(): Completable

    fun getEthplorerTokenAddressessBySymbol(symbol: String): Flowable<List<Pair<String, String>>>
}
