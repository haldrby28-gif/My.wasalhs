package com.waselha.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_admin_dashboard)

        findViewById<Button>(R.id.btnRestaurants).setOnClickListener {
            startActivity(Intent(this, RestaurantsActivity::class.java))
        }

        findViewById<Button>(R.id.btnDrivers).setOnClickListener {
            startActivity(Intent(this, DriversActivity::class.java))
        }

        findViewById<Button>(R.id.btnCustomers).setOnClickListener {
            startActivity(Intent(this, CustomersActivity::class.java))
        }

        findViewById<Button>(R.id.btnOrders).setOnClickListener {
            startActivity(Intent(this, OrdersActivity::class.java))
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
