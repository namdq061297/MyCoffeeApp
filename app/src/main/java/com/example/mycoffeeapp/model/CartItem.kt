package com.example.mycoffeeapp.model

data class CartItem(
    val product: Product,
    val size: CoffeeSize,
    val count: Int,
)
