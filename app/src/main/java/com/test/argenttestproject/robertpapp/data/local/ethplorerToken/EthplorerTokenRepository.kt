package com.test.argenttestproject.robertpapp.data.local.ethplorerToken

import io.reactivex.rxjava3.core.Completable

interface EthplorerTokenRepository {

    fun updateEthplorerTokens() : Completable
}
