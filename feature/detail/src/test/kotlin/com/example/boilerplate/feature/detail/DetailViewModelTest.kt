package com.example.boilerplate.feature.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.boilerplate.core.domain.repository.ItemRepository
import com.example.boilerplate.core.domain.usecase.GetItemByIdUseCase
import com.example.boilerplate.core.testing.MainDispatcherRule
import com.example.boilerplate.core.testing.data.testItem
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = mockk<ItemRepository>()

    private fun createViewModel() =
        DetailViewModel(
            savedStateHandle = SavedStateHandle(mapOf("id" to testItem.id)),
            getItemByIdUseCase = GetItemByIdUseCase(repository),
        )

    @Test
    fun `uiState initial value is Loading`() {
        every { repository.getItemById(testItem.id) } returns emptyFlow()
        assertEquals(DetailUiState.Loading, createViewModel().uiState.value)
    }

    @Test
    fun `uiState emits Success when item is found`() = runTest {
        every { repository.getItemById(testItem.id) } returns flowOf(testItem)
        createViewModel().uiState.test {
            skipItems(1)
            assertEquals(DetailUiState.Success(testItem), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Error when item is not found`() = runTest {
        every { repository.getItemById(testItem.id) } returns flowOf(null)
        createViewModel().uiState.test {
            skipItems(1)
            assertTrue(awaitItem() is DetailUiState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }
}