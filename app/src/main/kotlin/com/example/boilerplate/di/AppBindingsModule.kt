package com.example.boilerplate.di

import com.example.boilerplate.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppBindingsModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun providesBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    @Named("isDebug")
    fun providesIsDebug(): Boolean = BuildConfig.DEBUG
}
