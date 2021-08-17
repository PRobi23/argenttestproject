package com.test.argenttestproject.robertpapp.data.remote.response

data class EthplorerToken(
    val address: String?,
    val name: String?,
    val decimals: Int?,
    val symbol: String?,
    val totalSupply: String?,
    val owner: String?,
    val txsCount: Int?,
    val transfersCount: Int?,
    val lastUpdated: Int?,
    val slot: Int?,
    val issuancesCount: Int?,
    val holdersCount: Int?,
    val image: String?,
    val website: String?,
    val coingecko: String?,
    val ethTransfersCount: Int?,
    val opCount: Int?
)
