package com.example.boilerplate.feature.detail

import com.example.boilerplate.core.model.Item

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Success(val item: Item) : DetailUiState
    data class Error(val message: String) : DetailUiState
}
