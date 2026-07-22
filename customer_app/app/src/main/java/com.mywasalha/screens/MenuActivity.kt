package com.mywasalha.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mywasalha.R
import com.mywasalha.adapters.ProductAdapter
import com.mywasalha.models.Product

class MenuActivity : AppCompatActivity() {

    private lateinit var restaurantName: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartButton: Button
    private lateinit var adapter: ProductAdapter

    private val products = mutableListOf<Product>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        restaurantName = findViewById(R.id.txtRestaurantName)
        recyclerView = findViewById(R.id.productsRecyclerView)
        cartButton = findViewById(R.id.btnGoToCart)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(products)
        recyclerView.adapter = adapter

        val restaurantId = intent.getStringExtra("restaurantId") ?: ""
        val restaurantTitle = intent.getStringExtra("restaurantName") ?: ""

        restaurantName.text = restaurantTitle

        loadProducts(restaurantId)

        cartButton.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CartActivity::class.java
                )
            )
        }
    }

    private fun loadProducts(restaurantId: String) {

        db.collection("products")
            .whereEqualTo("restaurantId", restaurantId)
            .get()
            .addOnSuccessListener { documents ->

                products.clear()

                for (document in documents) {

                    val product = Product(
                        id = document.id,
                        name = document.getString("name") ?: "",
                        description = document.getString("description") ?: "",
                        price = document.getDouble("price") ?: 0.0,
                        restaurantId = document.getString("restaurantId") ?: ""
                    )

                    products.add(product)
                }

                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "فشل تحميل المنتجات",
                   
