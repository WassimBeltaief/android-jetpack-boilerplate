package com.example.boilerplate.core.network.api

import com.example.boilerplate.core.network.model.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("items")
    suspend fun getItems(): List<ItemResponse>

    @GET("items/{id}")
    suspend fun getItemById(
        @Path("id") id: String,
    ): ItemResponse
}
