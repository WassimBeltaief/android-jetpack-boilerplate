package com.example.boilerplate.core.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.boilerplate.core.database.entity.ItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseSeedCallback(
    private val daoProvider: () -> AppDatabase,
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            daoProvider().itemDao().insertItems(SeedData.items)
        }
    }

    object SeedData {
        val items = listOf(
            ItemEntity(
                id = "1",
                title = "First Item",
                description = "This is the first seed item.",
                imageUrl = "",
            ),
            ItemEntity(
                id = "2",
                title = "Second Item",
                description = "This is the second seed item.",
                imageUrl = "",
            ),
        )
    }
}
