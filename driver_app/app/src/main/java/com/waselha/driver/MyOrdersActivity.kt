package com.mywasalha.driver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class MyOrdersActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: AvailableOrderAdapter

    private val orders = mutableListOf<Order>()

    private val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_orders)



        recyclerView =
            findViewById(R.id.myOrdersRecyclerView)



        recyclerView.layoutManager =
            LinearLayoutManager(this)



        adapter =
            AvailableOrderAdapter(orders)



        recyclerView.adapter =
            adapter



        loadMyOrders()

    }



    private fun loadMyOrders() {


        val driverId = "CURRENT_DRIVER_ID"


        db.collection("orders")

            .whereEqualTo(
                "driverId",
                driverId
            )

            .whereEqualTo(
                "status",
                "accepted"
            )

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
