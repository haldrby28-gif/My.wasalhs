package com.waselha.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RestaurantsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_restaurants)

        recyclerView = findViewById(R.id.recyclerRestaurants)
        fab = findViewById(R.id.fabAddRestaurant)

        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            startActivity(
                Intent(this, AddRestaurantActivity::class.java)
            )
        }
    }
}
