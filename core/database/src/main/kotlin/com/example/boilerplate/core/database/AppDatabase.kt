package com.example.boilerplate.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boilerplate.core.database.dao.ItemDao
import com.example.boilerplate.core.database.entity.ItemEntity

@Database(
    entities = [ItemEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
