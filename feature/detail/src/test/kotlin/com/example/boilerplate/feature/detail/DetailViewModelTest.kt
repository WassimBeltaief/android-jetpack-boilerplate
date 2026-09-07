package com.example.boilerplate.feature.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.boilerplate.core.domain.usecase.GetItemByIdUseCase
import com.example.boilerplate.core.testing.MainDispatcherRule
import com.example.boilerplate.core.testing.data.testItem
import com.example.boilerplate.core.testing.repository.FakeItemRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeRepository = FakeItemRepository()
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel(
            savedStateHandle = SavedStateHandle(mapOf("id" to testItem.id)),
            getItemByIdUseCase = GetItemByIdUseCase(fakeRepository),
        )
    }

    @Test
    fun `uiState is Loading initially`() = runTest {
        viewModel.uiState.test {
            assertTrue(awaitItem() is DetailUiState.Loading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Success when item is found`() = runTest {
        fakeRepository.emitItem(testItem)

        viewModel.uiState.test {
            skipItems(1)
            val success = awaitItem() as DetailUiState.Success
            assertEquals(testItem, success.item)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Error when item is null`() = runTest {
        fakeRepository.emitItem(null)

        viewModel.uiState.test {
            skipItems(1)
            assertTrue(awaitItem() is DetailUiState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
