package com.test.argenttestproject.robertpapp.data.local.ethplorerToken

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface EthplorerTokenDao {
    @Query("SELECT * FROM ethplorerTokens")
    fun getAll(): Flowable<List<EthplorerTokenEntity>>

    @Query("SELECT * FROM ethplorerTokens WHERE symbol LIKE  '%' || :symbol || '%'")
    fun getAddresssBySymbol(symbol: String): Flowable<List<EthplorerTokenEntity>>

    @Insert
    fun insertAll(vararg tokens: EthplorerTokenEntity): Completable

    @Insert
    fun insertAll(tokens: List<EthplorerTokenEntity>): Completable

    @Query("DELETE FROM ethplorerTokens")
    fun deleteAll(): Completable
}
