package com.example.boilerplate.core.data.di

import com.example.boilerplate.core.data.repository.ItemRepositoryImplementation
import com.example.boilerplate.core.domain.repository.ItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindsItemRepository(implementation: ItemRepositoryImplementation): ItemRepository
}
