package com.mywasalha.models

data class Restaurant(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val coverImage: String = "",
    val category: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val phone: String = "",
    val rating: Float = 0.0f,
    val deliveryFee: Double = 0.0,
    val minimumOrder: Double = 0.0,
    val estimatedDeliveryTime: Int = 30,
    val isOpen: Boolean = true
)
