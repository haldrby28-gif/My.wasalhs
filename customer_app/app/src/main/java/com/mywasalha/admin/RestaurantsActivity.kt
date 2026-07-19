package com.waselha.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addRestaurantButton: Button

    private val restaurantList = mutableListOf<Restaurant>()
    private lateinit var adapter: RestaurantAdapter

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_restaurants_admin)

        recyclerView = findViewById(R.id.recyclerRestaurants)
        addRestaurantButton = findViewById(R.id.btnAddRestaurant)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RestaurantAdapter(restaurantList)

        recyclerView.adapter = adapter

        addRestaurantButton.setOnClickListener {

            startActivity(
                Intent(this, AddRestaurantActivity::class.java)
            )

        }

        loadRestaurants()

    }

    override fun onResume() {
        super.onResume()
        loadRestaurants()
    }

    private fun loadRestaurants() {

        db.collection("stores")
            .get()
            .addOnSuccessListener {

                restaurantList.clear()

                for (doc in it) {

                    val restaurant = doc.toObject(Restaurant::class.java)

                    restaurantList.add(restaurant)

                }

                adapter.notifyDataSetChanged()

            }

    }

}
