package com.example.boilerplate.core.network.model

import com.example.boilerplate.core.model.Item
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("image_url") val imageUrl: String = "",
)

fun ItemResponse.toModel() =
    Item(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
    )
