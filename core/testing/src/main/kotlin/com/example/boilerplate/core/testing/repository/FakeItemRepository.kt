package com.example.boilerplate.core.testing.repository

import com.example.boilerplate.core.domain.repository.ItemRepository
import com.example.boilerplate.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeItemRepository : ItemRepository {
    private val itemsFlow = MutableStateFlow<List<Item>>(emptyList())
    private val itemByIdFlow = MutableStateFlow<Item?>(null)

    fun emit(items: List<Item>) {
        itemsFlow.value = items
    }

    fun emitItem(item: Item?) {
        itemByIdFlow.value = item
    }

    override fun getItems(): Flow<List<Item>> = itemsFlow

    override fun getItemById(id: String): Flow<Item?> = itemByIdFlow

    override suspend fun refreshItems() = Unit
}
