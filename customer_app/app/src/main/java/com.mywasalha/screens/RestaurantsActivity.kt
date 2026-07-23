package com.waselha.customer.screens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.waselha.customer.R
import com.waselha.customer.adapters.RestaurantAdapter
import com.waselha.customer.models.Restaurant

class RestaurantsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestaurantAdapter

    private val restaurants = mutableListOf<Restaurant>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        recyclerView = findViewById(R.id.restaurantsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RestaurantAdapter(restaurants)
        recyclerView.adapter = adapter

        loadRestaurants()
    }

    private fun loadRestaurants() {

        db.collection("restaurants")
            .get()
            .addOnSuccessListener { documents ->

                restaurants.clear()

                for (document in documents) {

                    val restaurant = Restaurant(
                        id = document.id,
                        name = document.getString("restaurantName") ?: "",
                        address = document.getString("address") ?: ""
                    )

                    restaurants.add(restaurant)
                }

                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "فشل تحميل المطاعم",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
