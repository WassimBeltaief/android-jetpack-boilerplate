package com.example.boilerplate.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.boilerplate.core.database.AppDatabase
import com.example.boilerplate.core.database.DatabaseSeedCallback
import com.example.boilerplate.core.database.dao.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        lateinit var database: AppDatabase
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "boilerplate.db",
        ).addCallback(DatabaseSeedCallback { database }).build()
        return database
    }

    @Provides
    fun providesItemDao(database: AppDatabase): ItemDao = database.itemDao()
}
