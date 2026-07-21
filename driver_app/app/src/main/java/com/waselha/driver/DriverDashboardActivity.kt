package com.mywasalha.driver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class DriverDashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_driver_dashboard)



        val availableOrders =
            findViewById<Button>(R.id.btnAvailableOrders)


        val myOrders =
            findViewById<Button>(R.id.btnMyOrders)



        availableOrders.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AvailableOrdersActivity::class.java
                )
            )

        }



        myOrders.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    MyOrdersActivity::class.java
                )
            )

        }

    }

}
