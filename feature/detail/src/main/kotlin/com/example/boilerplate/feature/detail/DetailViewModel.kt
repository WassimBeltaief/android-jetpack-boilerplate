package com.example.boilerplate.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boilerplate.core.domain.usecase.GetItemByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        getItemByIdUseCase: GetItemByIdUseCase,
    ) : ViewModel() {
        private val itemId: String = checkNotNull(savedStateHandle["id"]) { "item id is required" }

        val uiState: StateFlow<DetailUiState> =
            getItemByIdUseCase(itemId)
                .map { item ->
                    if (item != null) {
                        DetailUiState.Success(item)
                    } else {
                        DetailUiState.Error("Item not found")
                    }
                }.catch { emit(DetailUiState.Error(it.message ?: "Unknown error")) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = DetailUiState.Loading,
                )
    }
