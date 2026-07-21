package com.waselha.driver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DriverDashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_driver_dashboard)

        auth = FirebaseAuth.getInstance()

        val btnAvailableOrders = findViewById<Button>(R.id.btnAvailableOrders)
        val btnCurrentOrder = findViewById<Button>(R.id.btnCurrentOrder)
        val btnEarnings = findViewById<Button>(R.id.btnEarnings)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnAvailableOrders.setOnClickListener {
            startActivity(Intent(this, AvailableOrdersActivity::class.java))
        }

        btnCurrentOrder.setOnClickListener {
            startActivity(Intent(this, CurrentOrderActivity::class.java))
        }

        btnEarnings.setOnClickListener {
            startActivity(Intent(this, EarningsActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
