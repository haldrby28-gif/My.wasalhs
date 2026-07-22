package com.mywasalha.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mywasalha.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnRestaurants: Button
    private lateinit var btnMyOrders: Button
    private lateinit var btnProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRestaurants = findViewById(R.id.btnRestaurants)
        btnMyOrders = findViewById(R.id.btnMyOrders)
        btnProfile = findViewById(R.id.btnProfile)

        btnRestaurants.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RestaurantsActivity::class.java
                )
            )
        }

        btnMyOrders.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    OrderTrackingActivity::class.java
                )
            )
        }

        btnProfile.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
        }
    }
}
