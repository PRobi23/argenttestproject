package com.test.argenttestproject.robertpapp.data.remote.response

data class EthplorerTokenPrice(
    val rate: Double,
    val diff: Double,
    val diff7d: Double,
    val ts: Int,
    val marketCapUsd: Double,
    val availableSupply: Double,
    val volume24h: Double,
    val diff30d: Double,
    val volDiff1: Double,
    val volDiff7: Double,
    val volDiff30: Double,
    val currency: String
)
