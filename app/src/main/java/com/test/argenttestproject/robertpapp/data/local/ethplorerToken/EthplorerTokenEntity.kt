package com.test.argenttestproject.robertpapp.data.local.ethplorerToken

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ethplorerTokens")
data class EthplorerTokenEntity(
    @PrimaryKey @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "decimals") val decimals: Int,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "totalSupply") val totalSupply: String,
    @ColumnInfo(name = "owner") val owner: String,
    @ColumnInfo(name = "txsCount") val txsCount: Int,
    @ColumnInfo(name = "transfersCount") val transfersCount: Int,
    @ColumnInfo(name = "lastUpdated") val lastUpdated: Int,
    @ColumnInfo(name = "slot") val slot: Int,
    @ColumnInfo(name = "issuancesCount") val issuancesCount: Int,
    @ColumnInfo(name = "holdersCount") val holdersCount: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "website") val website: String,
    @ColumnInfo(name = "coingecko") val coingecko: String,
    @ColumnInfo(name = "ethTransfersCount") val ethTransfersCount: Int,
    @ColumnInfo(name = "opCount") val opCount: Int
)
