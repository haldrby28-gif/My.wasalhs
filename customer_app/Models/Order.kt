package com.mywasalha.models

data class Order(
    val id: String = "",
    val userId: String = "",
    val restaurantId: String = "",
    val driverId: String = "",
    val items: List<Cart> = emptyList(),
    val totalAmount: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val finalAmount: Double = 0.0,
    val paymentMethod: String = "cash",
    val paymentStatus: String = "pending",
    val orderStatus: String = "pending",
    val deliveryAddress: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
