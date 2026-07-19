package com.waselha.admin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddRestaurantActivity : AppCompatActivity() {

    private lateinit var restaurantName: EditText
    private lateinit var ownerName: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var city: EditText
    private lateinit var address: EditText
    private lateinit var saveButton: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_restaurant)

        restaurantName = findViewById(R.id.etRestaurantName)
        ownerName = findViewById(R.id.etOwnerName)
        phone = findViewById(R.id.etPhone)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        city = findViewById(R.id.etCity)
        address = findViewById(R.id.etAddress)
        saveButton = findViewById(R.id.btnSaveRestaurant)

        saveButton.setOnClickListener {
            saveRestaurant()
        }
    }

    private fun saveRestaurant() {

        val data = hashMapOf(
            "restaurantName" to restaurantName.text.toString().trim(),
            "ownerName" to ownerName.text.toString().trim(),
            "phone" to phone.text.toString().trim(),
            "email" to email.text.toString().trim(),
            "password" to password.text.toString().trim(),
            "city" to city.text.toString().trim(),
            "address" to address.text.toString().trim(),
            "
