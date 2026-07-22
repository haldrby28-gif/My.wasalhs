package com.mywasalha.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FirebaseRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getRestaurants() =
        db.collection("restaurants")

    fun getProducts(restaurantId: String) =
        db.collection("products")
            .whereEqualTo("restaurantId", restaurantId)

    fun getOrders(customerId: String) =
        db.collection("orders")
            .whereEqualTo("customerId", customerId)
            .orderBy("createdAt", Query.Direction.DESCENDING)

    fun createOrder(order: HashMap<String, Any>) =
        db.collection("orders").add(order)

    fun updateOrderStatus(orderId: String, status: String) =
        db.collection("orders")
            .document(orderId)
            .update("status", status)
}
