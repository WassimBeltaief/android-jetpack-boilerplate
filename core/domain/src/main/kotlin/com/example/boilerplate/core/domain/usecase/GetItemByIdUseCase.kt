package com.example.boilerplate.core.domain.usecase

import com.example.boilerplate.core.domain.repository.ItemRepository
import com.example.boilerplate.core.model.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemByIdUseCase
    @Inject
    constructor(
        private val repository: ItemRepository,
    ) {
        operator fun invoke(id: String): Flow<Item?> = repository.getItemById(id)
    }
