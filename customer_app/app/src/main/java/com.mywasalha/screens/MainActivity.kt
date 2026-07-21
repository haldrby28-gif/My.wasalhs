package com.mywasalha.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mywasalha.R
import com.mywasalha.adapters.RestaurantAdapter
import com.mywasalha.models.Restaurant


class RestaurantsActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: RestaurantAdapter


    private val restaurants =
        mutableListOf<Restaurant>()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_restaurants)



        recyclerView =
            findViewById(R.id.restaurantsRecyclerView)



        recyclerView.layoutManager =
            LinearLayoutManager(this)



        adapter =
            RestaurantAdapter(restaurants)



        recyclerView.adapter =
            adapter


        loadRestaurants()

    }



    private fun loadRestaurants() {


        // بيانات تجريبية مؤقتة
        // لاحقاً سنربطها مع Firebase


        restaurants.add(
            Restaurant(
                "1",
                "مطعم تجريبي",
                "فرشوط"
            )
        )


        adapter.notifyDataSetChanged()

    }

}
