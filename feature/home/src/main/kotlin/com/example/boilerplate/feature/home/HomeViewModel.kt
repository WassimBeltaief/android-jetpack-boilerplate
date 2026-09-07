package com.example.boilerplate.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boilerplate.core.domain.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        getItemsUseCase: GetItemsUseCase,
    ) : ViewModel() {
        val uiState: StateFlow<HomeUiState> =
            getItemsUseCase()
                .map<_, HomeUiState> { HomeUiState.Success(it) }
                .catch { emit(HomeUiState.Error(it.message ?: "Unknown error")) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = HomeUiState.Loading,
                )

        private val _events = MutableSharedFlow<HomeEvent>(extraBufferCapacity = 1)
        val events = _events.asSharedFlow()

        fun onItemClick(id: String) {
            _events.tryEmit(HomeEvent.NavigateToDetail(id))
        }
    }

sealed interface HomeEvent {
    data class NavigateToDetail(
        val id: String,
    ) : HomeEvent
}
