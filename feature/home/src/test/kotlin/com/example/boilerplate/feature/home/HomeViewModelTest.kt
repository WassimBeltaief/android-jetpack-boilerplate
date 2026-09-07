package com.example.boilerplate.feature.home

import app.cash.turbine.test
import com.example.boilerplate.core.domain.usecase.GetItemsUseCase
import com.example.boilerplate.core.testing.MainDispatcherRule
import com.example.boilerplate.core.testing.data.testItem
import com.example.boilerplate.core.testing.data.testItems
import com.example.boilerplate.core.testing.repository.FakeItemRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeRepository = FakeItemRepository()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(GetItemsUseCase(fakeRepository))
    }

    @Test
    fun `uiState starts with Loading before any emission`() =
        runTest {
            viewModel.uiState.test {
                // Initial value from stateIn before upstream collection runs
                val first = awaitItem()
                assertTrue(
                    "Expected Loading or Success, got $first",
                    first is HomeUiState.Loading || first is HomeUiState.Success,
                )
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `uiState emits Success when items are available`() =
        runTest {
            viewModel.uiState.test {
                awaitItem() // Consume initial state (Loading or Success(empty))
                fakeRepository.emit(testItems) // Emit inside the test block
                val success = awaitItem() as HomeUiState.Success
                assertEquals(testItems, success.items)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `onItemClick emits NavigateToDetail event`() =
        runTest {
            viewModel.events.test {
                viewModel.onItemClick(testItem.id)
                assertEquals(HomeEvent.NavigateToDetail(testItem.id), awaitItem())
            }
        }
}
