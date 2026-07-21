package com.waselha.driver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AvailableOrdersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AvailableOrderAdapter

    private val orderList = ArrayList<Order>()

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_available_orders)

        recyclerView = findViewById(R.id.recyclerOrders)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = AvailableOrderAdapter(orderList)
        recyclerView.adapter = adapter

        loadOrders()
    }

    override fun onResume() {
        super.onResume()
        loadOrders()
    }

    private fun loadOrders() {

        db.collection("orders")
            .whereEqualTo("orderStatus", "READY")
            .get()
            .addOnSuccessListener { result ->

                orderList.clear()

                for (doc in result) {

                    val order = doc.toObject(Order::class.java)
                    order.id = doc.id

                    orderList.add(order)
                }

                adapter.notifyDataSetChanged()
            }
    }
}
