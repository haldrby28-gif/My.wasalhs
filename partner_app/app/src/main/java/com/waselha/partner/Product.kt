package com.waselha.partner

data class Product(

    var id: String = "",

    var restaurantId: String = "",

    var categoryId: String = "",

    var name: String = "",

    var description: String = "",

    var price: Double = 0.0,

    var imageUrl: String = "",

    var available: Boolean = true,

    var createdAt: Long = 0

)
