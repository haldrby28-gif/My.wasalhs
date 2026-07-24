package com.waselha.customer.screens


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mywasalha.R
import com.mywasalha.adapters.CartAdapter
import com.mywasalha.firebase.CartManager

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalText: TextView
    private lateinit var checkoutButton: Button
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.cartRecyclerView)
        totalText = findViewById(R.id.txtTotal)
        checkoutButton = findViewById(R.id.btnCheckout)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CartAdapter(CartManager.getItems())
        recyclerView.adapter = adapter

        updateCart()

        checkoutButton.setOnClickListener {

            if (CartManager.getItems().isEmpty()) {

                Toast.makeText(
                    this,
                    "السلة فارغة",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                startActivity(
                    Intent(
                        this,
                        CheckoutActivity::class.java
                    )
                )

            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateCart()
    }

    private fun updateCart() {

        adapter.notifyDataSetChanged()

        totalText.text =
            "الإجمالي: ${CartManager.getTotal()} جنيه"

    }
}
