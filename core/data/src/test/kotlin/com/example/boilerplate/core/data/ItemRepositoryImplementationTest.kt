package com.example.boilerplate.core.data

import app.cash.turbine.test
import com.example.boilerplate.core.data.repository.ItemRepositoryImplementation
import com.example.boilerplate.core.database.dao.ItemDao
import com.example.boilerplate.core.database.entity.ItemEntity
import com.example.boilerplate.core.network.api.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ItemRepositoryImplementationTest {
    private val testDispatcher = StandardTestDispatcher()
    private val itemDao = mockk<ItemDao>(relaxed = true)
    private val apiService = mockk<ApiService>(relaxed = true)

    private val repository =
        ItemRepositoryImplementation(
            itemDao = itemDao,
            apiService = apiService,
            ioDispatcher = testDispatcher,
        )

    @Test
    fun `getItems maps entities to domain models`() =
        runTest {
            val entities =
                MutableStateFlow(
                    listOf(ItemEntity(id = "1", title = "T", description = "D", imageUrl = "")),
                )
            coEvery { itemDao.getItems() } returns entities

            repository.getItems().test {
                val items = awaitItem()
                assertEquals(1, items.size)
                assertEquals("1", items[0].id)
                cancelAndIgnoreRemainingEvents()
            }
        }
}
