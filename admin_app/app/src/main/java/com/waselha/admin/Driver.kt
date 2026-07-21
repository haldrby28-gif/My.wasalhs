package com.waselha.admin

data class Driver(

    var id: String = "",

    var fullName: String = "",

    var phone: String = "",

    var email: String = "",

    var city: String = "",

    var vehicleType: String = "",

    var vehicleNumber: String = "",

    var active: Boolean = true,

    var available: Boolean = false,

    var uid: String = "",

    var createdAt: Long = 0

)
