package com.mywasalha

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val restaurants = findViewById<Button>(R.id.restaurantsBtn)
        val shops = findViewById<Button>(R.id.shopsBtn)
        val pharmacy = findViewById<Button>(R.id.pharmacyBtn)
        val orders = findViewById<Button>(R.id.ordersBtn)


        restaurants.setOnClickListener {
            Toast.makeText(this, "قسم المطاعم", Toast.LENGTH_SHORT).show()
        }

        shops.setOnClickListener {
            Toast.makeText(this, "قسم المحلات", Toast.LENGTH_SHORT).show()
        }

        pharmacy.setOnClickListener {
            Toast.makeText(this, "قسم الصيدليات", Toast.LENGTH_SHORT).show()
        }

        orders.setOnClickListener {
            Toast.makeText(this, "قسم طلباتي", Toast.LENGTH_SHORT).show()
        }

    }
}
