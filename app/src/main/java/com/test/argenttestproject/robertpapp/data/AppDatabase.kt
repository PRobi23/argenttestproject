package com.test.argenttestproject.robertpapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenDao
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenEntity


@Database(
    entities = [
        EthplorerTokenEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getEthplorerTokenDao(): EthplorerTokenDao
}
