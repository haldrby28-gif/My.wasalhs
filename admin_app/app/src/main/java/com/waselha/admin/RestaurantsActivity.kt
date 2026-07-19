package com.waselha.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestaurantAdapter
    private lateinit var fab: FloatingActionButton

    private val restaurantList = ArrayList<Restaurant>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_restaurants)

        recyclerView = findViewById(R.id.recyclerRestaurants)
        fab = findViewById(R.id.fabAddRestaurant)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RestaurantAdapter(restaurantList)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            startActivity(Intent(this, AddRestaurantActivity::class.java))
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
            .addOnSuccessListener { result ->

                restaurantList.clear()

                for (document in result) {

                    val restaurant = document.toObject(Restaurant::class.java)
                    restaurant.id = document.id

                    restaurantList.add(restaurant)
                }

                adapter.notifyDataSetChanged()

            }

    }
}
