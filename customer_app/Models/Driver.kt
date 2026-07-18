package com.mywasalha.models

data class Driver(
    val id: String = "",
    val fullName: String = "",
    val phone: String = "",
    val profileImage: String = "",
    val vehicleType: String = "",
    val vehicleNumber: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val isAvailable: Boolean = true,
    val currentOrderId: String = "",
    val rating: Float = 5.0f,
    val createdAt: Long = System.currentTimeMillis()
)
