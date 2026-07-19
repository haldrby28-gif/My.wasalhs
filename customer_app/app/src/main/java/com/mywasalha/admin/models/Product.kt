package com.waselha.admin.models

data class Product(

    var id: String = "",
    var storeId: String = "",
    var name: String = "",
    var description: String = "",
    var category: String = "",
    var price: Double = 0.0,
    var imageUrl: String = "",
    var available: Boolean = true,
    var createdAt: Long = System.currentTimeMillis()

)
