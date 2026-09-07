package com.example.boilerplate.feature.home

import androidx.compose.runtime.Immutable
import com.example.boilerplate.core.model.Item

sealed interface HomeUiState {
    data object Loading : HomeUiState

    @Immutable
    data class Success(
        val items: List<Item>,
    ) : HomeUiState

    data class Error(
        val message: String,
    ) : HomeUiState
}
