package com.mywasalha

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mywasalha.adapters.RestaurantAdapter
import com.mywasalha.models.Restaurant

class RestaurantsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestaurantAdapter
    private val restaurantList = mutableListOf<Restaurant>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        recyclerView = findViewById(R.id.restaurantsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RestaurantAdapter(restaurantList)
        recyclerView.adapter = adapter

        loadRestaurants()
    }

    private fun loadRestaurants() {
        db.collection("stores")
            .get()
            .addOnSuccessListener { result ->
                restaurantList.clear()

                for (document in result) {
                    val restaurant = document.toObject(Restaurant::class.java)
                    restaurantList.add(restaurant)
                }

                adapter.notifyDataSetChanged()
            }
    }
}
