package com.mywasalha.driver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class AvailableOrdersActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AvailableOrderAdapter

    private val orders = mutableListOf<Order>()

    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_available_orders)


        recyclerView =
            findViewById(R.id.ordersRecyclerView)


        recyclerView.layoutManager =
            LinearLayoutManager(this)


        adapter =
            AvailableOrderAdapter(orders)


        recyclerView.adapter =
            adapter


        loadOrders()
    }



    private fun loadOrders() {


        db.collection("orders")
            .whereEqualTo("status", "available")
            .get()
            .addOnSuccessListener { result ->


                orders.clear()


                for (document in result) {


                    val order =
                        document.toObject(Order::class.java)


                    order.id =
                        document.id


                    orders.add(order)
                }


                adapter.notifyDataSetChanged()
            }
    }
}
