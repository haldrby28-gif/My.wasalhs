package com.mywasalha.models

data class User(
    val id: String = "",
    val fullName: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val profileImage: String = "",
    val userType: String = "customer",
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
