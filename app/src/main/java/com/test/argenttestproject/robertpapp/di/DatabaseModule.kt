package com.test.argenttestproject.robertpapp.di

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.argenttestproject.robertpapp.data.AppDatabase
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenDao
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "argent-testproject-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single<EthplorerTokenDao> {
        getEthplorerTokenDao(get())
    }
}

fun getEthplorerTokenDao(db: AppDatabase): EthplorerTokenDao {
    return db.getEthplorerTokenDao()
}
