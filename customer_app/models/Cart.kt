
package com.mywasalha.models

data class Cart(
    val id: String = "",
    val userId: String = "",
    val productId: String = "",
    val productName: String = "",
    val productImage: String = "",
    val quantity: Int = 1,
    val price: Double = 0.0,
    val totalPrice: Double = 0.0,
    val restaurantId: String = ""
)
