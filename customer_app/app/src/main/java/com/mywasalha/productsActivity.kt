package com.mywasalha

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mywasalha.adapters.ProductAdapter
import com.mywasalha.models.Product

class ProductsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val productList = mutableListOf<Product>()

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_products)

        val storeId = intent.getStringExtra("storeId") ?: ""

        recyclerView = findViewById(R.id.productsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        import com.mywasalha.utils.CartManager

CartManager.addProduct(product)

Toast.makeText(
    this,
    "تمت إضافة ${product.name} إلى السلة",
    Toast.LENGTH_SHORT
).show()

        recyclerView.adapter = adapter

        loadProducts(storeId)
    }

    private fun loadProducts(storeId: String) {

        db.collection("products")
            .whereEqualTo("storeId", storeId)
            .get()
            .addOnSuccessListener { result ->

                productList.clear()

                for (document in result) {

                    val product = document.toObject(Product::class.java)

                    productList.add(product)
                }

                adapter.notifyDataSetChanged()
            }
    }
}
