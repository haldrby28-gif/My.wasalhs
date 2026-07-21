package com.waselha.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class DriversActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DriverAdapter
    private lateinit var fab: FloatingActionButton

    private val driverList = ArrayList<Driver>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_drivers)

        recyclerView = findViewById(R.id.recyclerDrivers)
        fab = findViewById(R.id.fabAddDriver)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DriverAdapter(driverList)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            startActivity(Intent(this, AddDriverActivity::class.java))
        }

        loadDrivers()
    }

    override fun onResume() {
        super.onResume()
        loadDrivers()
    }

    private fun loadDrivers() {

        db.collection("drivers")
            .get()
            .addOnSuccessListener { result ->

                driverList.clear()

                for (document in result) {

                    val driver = document.toObject(Driver::class.java)
                    driver.id = document.id

                    driverList.add(driver)
                }

                adapter.notifyDataSetChanged()
            }
    }
}
