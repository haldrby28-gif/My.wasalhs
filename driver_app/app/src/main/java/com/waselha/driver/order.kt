package com.waselha.driver

data class Order(

    var id: String = "",

    var orderNumber: String = "",

    var customerId: String = "",
    var customerName: String = "",
    var customerPhone: String = "",

    var restaurantId: String = "",
    var restaurantName: String = "",

    var driverId: String = "",
    var driverName: String = "",

    var totalPrice: Double = 0.0,

    var paymentMethod: String = "",

    var orderStatus: String = "",

    var deliveryAddress: String = "",

    var notes: String = "",

    var createdAt: Long = 0
)
