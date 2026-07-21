package com.waselha.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class OrdersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderAdapter

    private val orderList = ArrayList<Order>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_orders)

        recyclerView = findViewById(R.id.recyclerOrders)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = OrderAdapter(orderList)
        recyclerView.adapter = adapter

        loadOrders()
    }

    override fun onResume() {
        super.onResume()
        loadOrders()
    }

    private fun loadOrders() {

        db.collection("orders")
            .orderBy("createdAt")
            .get()
            .addOnSuccessListener { result ->

                orderList.clear()

                for (document in result) {

                    val order = document.toObject(Order::class.java)
                    order.id = document.id

                    orderList.add(order)
                }

                adapter.notifyDataSetChanged()
            }
    }
}
