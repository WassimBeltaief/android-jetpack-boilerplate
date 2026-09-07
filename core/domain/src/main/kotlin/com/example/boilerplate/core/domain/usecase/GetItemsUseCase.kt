package com.example.boilerplate.core.domain.usecase

import com.example.boilerplate.core.domain.repository.ItemRepository
import com.example.boilerplate.core.model.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository,
) {
    operator fun invoke(): Flow<List<Item>> = repository.getItems()
}
