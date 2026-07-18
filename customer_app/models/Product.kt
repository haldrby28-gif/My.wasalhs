package com.mywasalha.models

data class Product(
    val id: String = "",
    val restaurantId: String = "",
    val categoryId: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val price: Double = 0.0,
    val discountPrice: Double? = null,
    val isAvailable: Boolean = true,
    val preparationTime: Int = 15
)
