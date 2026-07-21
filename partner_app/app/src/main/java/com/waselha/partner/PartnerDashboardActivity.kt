package com.waselha.partner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PartnerDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_partner_dashboard)

        val btnProducts = findViewById<Button>(R.id.btnProducts)
        val btnCategories = findViewById<Button>(R.id.btnCategories)
        val btnOrders = findViewById<Button>(R.id.btnOrders)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnSettings = findViewById<Button>(R.id.btnSettings)

        btnProducts.setOnClickListener {
            startActivity(Intent(this, ProductsActivity::class.java))
        }

        btnCategories.setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }

        btnOrders.setOnClickListener {
            startActivity(Intent(this, OrdersActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, RestaurantProfileActivity::class.java))
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
