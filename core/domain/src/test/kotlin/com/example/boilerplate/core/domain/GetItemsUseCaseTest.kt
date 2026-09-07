package com.example.boilerplate.core.domain

import app.cash.turbine.test
import com.example.boilerplate.core.domain.repository.ItemRepository
import com.example.boilerplate.core.domain.usecase.GetItemsUseCase
import com.example.boilerplate.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetItemsUseCaseTest {
    private val fakeRepository =
        object : ItemRepository {
            private val flow = MutableStateFlow<List<Item>>(emptyList())

            fun emit(items: List<Item>) {
                flow.value = items
            }

            override fun getItems(): Flow<List<Item>> = flow

            override fun getItemById(id: String): Flow<Item?> = MutableStateFlow(null)

            override suspend fun refreshItems() = Unit
        }

    @Test
    fun `returns items from repository`() =
        runTest {
            val items = listOf(Item(id = "1", title = "Test", description = "Desc"))
            fakeRepository.emit(items)

            GetItemsUseCase(fakeRepository).invoke().test {
                assertEquals(items, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
}
