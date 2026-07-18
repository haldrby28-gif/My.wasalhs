package com.mywasalha

import android.content.Intent
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

            val intent = Intent(this, RestaurantsActivity::class.java)
            startActivity(intent)

        }


        shops.setOnClickListener {

    val intent = Intent(this, ShopsActivity::class.java)
    startActivity(intent)

}

        pharmacy.setOnClickListener {

    val intent = Intent(this, PharmacyActivity::class.java)
    startActivity(intent)

}

        orders.setOnClickListener {
            Toast.makeText(this, "قسم طلباتي", Toast.LENGTH_SHORT).show()
        }

    }
}
