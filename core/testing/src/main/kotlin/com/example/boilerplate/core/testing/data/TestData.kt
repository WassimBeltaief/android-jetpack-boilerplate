package com.example.boilerplate.core.testing.data

import com.example.boilerplate.core.model.Item

val testItems =
    listOf(
        Item(id = "1", title = "First Item", description = "First description"),
        Item(id = "2", title = "Second Item", description = "Second description"),
        Item(id = "3", title = "Third Item", description = "Third description"),
    )

val testItem = testItems.first()
