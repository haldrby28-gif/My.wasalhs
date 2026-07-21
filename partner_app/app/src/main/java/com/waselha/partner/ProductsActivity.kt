package com.waselha.partner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProductsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var fab: FloatingActionButton

    private val productList = ArrayList<Product>()

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_products)

        recyclerView = findViewById(R.id.recyclerProducts)
        fab = findViewById(R.id.fabAddProduct)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            startActivity(
                Intent(this, AddProductActivity::class.java)
            )
        }

        loadProducts()
    }

    override fun onResume() {
        super.onResume()
        loadProducts()
    }

    private fun loadProducts() {

        val uid = auth.currentUser?.uid ?: return

        db.collection("products")
            .whereEqualTo("restaurantId", uid)
            .get()
            .addOnSuccessListener { result ->

                productList.clear()

                for (document in result) {

                    val product = document.toObject(Product::class.java)
                    product.id = document.id

                    productList.add(product)
                }

                adapter.notifyDataSetChanged()
            }
    }
}
