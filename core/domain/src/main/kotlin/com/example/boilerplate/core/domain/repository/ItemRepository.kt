package com.example.boilerplate.core.domain.repository

import com.example.boilerplate.core.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems(): Flow<List<Item>>

    fun getItemById(id: String): Flow<Item?>

    suspend fun refreshItems()
}
