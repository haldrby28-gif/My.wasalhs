package com.mywasalha.models

data class CartItem(
    val productId: String = "",
    val name: String = "",
    val price: Double = 0.0,
    var quantity: Int = 1
)
