package com.example.boilerplate.core.domain

import app.cash.turbine.test
import com.example.boilerplate.core.domain.repository.ItemRepository
import com.example.boilerplate.core.domain.usecase.GetItemsUseCase
import com.example.boilerplate.core.model.Item
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetItemsUseCaseTest {
    private val repository = mockk<ItemRepository>()

    @Test
    fun `returns items from repository`() = runTest {
        val items = listOf(Item(id = "1", title = "Test", description = "Desc"))
        every { repository.getItems() } returns flowOf(items)

        GetItemsUseCase(repository).invoke().test {
            assertEquals(items, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}