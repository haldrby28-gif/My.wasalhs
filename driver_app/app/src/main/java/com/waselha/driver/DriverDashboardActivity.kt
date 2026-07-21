package com.mywasalha.driver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class DriverDashboardActivity : AppCompatActivity() {


    private lateinit var availableOrdersButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_driver_dashboard)



        availableOrdersButton =
            findViewById(R.id.btnAvailableOrders)



        availableOrdersButton.setOnClickListener {


            val intent =
                Intent(
                    this,
                    AvailableOrdersActivity::class.java
                )


            startActivity(intent)

        }

    }

}
