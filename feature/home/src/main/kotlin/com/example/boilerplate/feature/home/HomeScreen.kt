package com.example.boilerplate.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boilerplate.core.model.Item
import com.example.boilerplate.core.ui.component.ErrorMessage
import com.example.boilerplate.core.ui.component.ItemCard
import com.example.boilerplate.core.ui.component.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Home") }) },
        modifier = modifier,
    ) { padding ->
        when (uiState) {
            is HomeUiState.Loading -> {
                LoadingIndicator(
                    modifier = Modifier.padding(padding),
                )
            }

            is HomeUiState.Error -> {
                ErrorMessage(
                    message = uiState.message,
                    modifier = Modifier.padding(padding),
                )
            }

            is HomeUiState.Success -> {
                ItemList(
                    items = uiState.items,
                    onItemClick = onItemClick,
                    modifier = Modifier.padding(padding),
                )
            }
        }
    }
}

@Composable
private fun ItemList(
    items: List<Item>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        items(items = items, key = { it.id }) { item ->
            ItemCard(
                item = item,
                onClick = { onItemClick(item.id) },
            )
        }
    }
}
