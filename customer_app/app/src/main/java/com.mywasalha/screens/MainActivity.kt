package com.waselha.customer.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.waselha.customer.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnRestaurants: Button
    private lateinit var btnOrders: Button
    private lateinit var btnProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRestaurants = findViewById(R.id.btnRestaurants)
        btnOrders = findViewById(R.id.btnOrders)
        btnProfile = findViewById(R.id.btnProfile)

        btnRestaurants.setOnClickListener {
            startActivity(
                Intent(this, RestaurantsActivity::class.java)
            )
        }

        btnOrders.setOnClickListener {
            startActivity(
                Intent(this, OrderTrackingActivity::class.java)
            )
        }

        btnProfile.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
    }
}
