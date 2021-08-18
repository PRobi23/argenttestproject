package com.test.argenttestproject.robertpapp.data.local.ethplorerToken

import com.test.argenttestproject.robertpapp.data.remote.api.EthplorerApi
import com.test.argenttestproject.robertpapp.data.remote.response.ethplorer.EthplorerToken
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class EthplorerTokenRepositoryImpl(
    private val ethplorerApi: EthplorerApi,
    private val ethplorerTokenDao: EthplorerTokenDao
) : EthplorerTokenRepository {

    override fun updateEthplorerTokens() =
        ethplorerTokenDao.deleteAll().andThen(
            ethplorerApi.getTopTokens()
                .flatMapCompletable { etphlorerTokens ->
                    ethplorerTokenDao.insertAll(
                        etphlorerTokenResponseToEntities(etphlorerTokens.tokens)
                    )
                }
        )

    override fun getEthplorerTokenAddressessBySymbol(symbol: String): Flowable<List<String>> =
        ethplorerTokenDao.getAddresssBySymbol(symbol)

    private fun etphlorerTokenResponseToEntities(response: List<EthplorerToken>) = response.map {
        EthplorerTokenEntity(
            address = it.address ?: "",
            name = it.name,
            decimals = it.decimals ?: 0,
            symbol = it.symbol ?: "",
            totalSupply = it.totalSupply ?: "",
            owner = it.owner ?: "",
            txsCount = it.txsCount ?: 0,
            transfersCount = it.transfersCount ?: 0,
            lastUpdated = it.lastUpdated ?: 0,
            slot = it.slot ?: 0,
            issuancesCount = it.issuancesCount ?: 0,
            holdersCount = it.holdersCount ?: 0,
            image = it.image ?: "",
            website = it.website ?: "",
            coingecko = it.coingecko ?: "",
            ethTransfersCount = it.ethTransfersCount ?: 0,
            opCount = it.opCount ?: 0
        )
    }
}
