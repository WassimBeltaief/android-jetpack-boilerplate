package com.example.boilerplate.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boilerplate.core.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getItems(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItemById(id: String): Flow<ItemEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemEntity>)

    @Query("DELETE FROM items")
    suspend fun deleteAllItems()
}
