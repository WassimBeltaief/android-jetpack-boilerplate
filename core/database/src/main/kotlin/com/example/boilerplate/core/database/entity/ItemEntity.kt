package com.example.boilerplate.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boilerplate.core.model.Item

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
)

fun ItemEntity.toModel() = Item(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
)

fun Item.toEntity() = ItemEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
)
