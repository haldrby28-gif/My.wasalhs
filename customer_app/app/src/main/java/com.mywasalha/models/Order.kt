package com.mywasalha.models

data class Order(

    var id: String = "",

    var customerId: String = "",

    var restaurantId: String = "",

    var address: String = "",

    var totalPrice: Double = 0.0,

    var status: String = "available",

    var driverId: String = "",

    var createdAt: Long = 0,

    var items: List<CartItem> = emptyList()

)
