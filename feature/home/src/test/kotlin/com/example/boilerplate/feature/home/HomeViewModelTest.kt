package com.example.boilerplate.feature.home

import app.cash.turbine.test
import com.example.boilerplate.core.domain.repository.ItemRepository
import com.example.boilerplate.core.domain.usecase.GetItemsUseCase
import com.example.boilerplate.core.testing.MainDispatcherRule
import com.example.boilerplate.core.testing.data.testItem
import com.example.boilerplate.core.testing.data.testItems
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = mockk<ItemRepository>()

    private fun createViewModel() = HomeViewModel(GetItemsUseCase(repository))

    @Test
    fun `uiState initial value is Loading`() {
        every { repository.getItems() } returns emptyFlow()
        assertEquals(HomeUiState.Loading, createViewModel().uiState.value)
    }

    @Test
    fun `uiState emits Success when repository emits items`() =
        runTest {
            every { repository.getItems() } returns flowOf(testItems)
            createViewModel().uiState.test {
                assertEquals(HomeUiState.Success(testItems), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `uiState emits Error when repository throws`() =
        runTest {
            every { repository.getItems() } returns flow { throw RuntimeException("network error") }
            createViewModel().uiState.test {
                assertTrue(awaitItem() is HomeUiState.Error)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `onItemClick emits NavigateToDetail event`() =
        runTest {
            every { repository.getItems() } returns emptyFlow()
            val viewModel = createViewModel()
            viewModel.events.test {
                viewModel.onItemClick(testItem.id)
                assertEquals(HomeEvent.NavigateToDetail(testItem.id), awaitItem())
            }
        }
}
