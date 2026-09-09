package com.example.boilerplate.core.data.repository

import com.example.boilerplate.core.common.dispatcher.IoDispatcher
import com.example.boilerplate.core.database.dao.ItemDao
import com.example.boilerplate.core.database.entity.toEntity
import com.example.boilerplate.core.database.entity.toModel
import com.example.boilerplate.core.domain.repository.ItemRepository
import com.example.boilerplate.core.model.Item
import com.example.boilerplate.core.network.api.ApiService
import com.example.boilerplate.core.network.model.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryImplementation @Inject constructor(
    private val itemDao: ItemDao,
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ItemRepository {
    override fun getItems(): Flow<List<Item>> = itemDao.getItems().map { entities -> entities.map { it.toModel() } }

    override fun getItemById(id: String): Flow<Item?> = itemDao.getItemById(id).map { it?.toModel() }

    override suspend fun refreshItems() =
        withContext(ioDispatcher) {
            val items = apiService.getItems().map { it.toModel().toEntity() }
            itemDao.deleteAllItems()
            itemDao.insertItems(items)
        }
}
