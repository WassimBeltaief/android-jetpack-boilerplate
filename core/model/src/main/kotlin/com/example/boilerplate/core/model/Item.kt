package com.example.boilerplate.core.model

data class Item(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String = "",
)
