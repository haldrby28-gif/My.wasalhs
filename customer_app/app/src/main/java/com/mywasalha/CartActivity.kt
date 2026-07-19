package com.mywasalha

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mywasalha.adapters.CartAdapter
import com.mywasalha.utils.CartManager

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPrice: TextView
    private lateinit var checkoutBtn: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.cartRecyclerView)
        totalPrice = findViewById(R.id.totalPriceText)
        checkoutBtn = findViewById(R.id.checkoutBtn)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CartAdapter(CartManager.getItems())

        totalPrice.text = "الإجمالي: ${CartManager.getTotal()} جنيه"

        checkoutBtn.setOnClickListener {
            saveOrder()
        }
    }

    private fun saveOrder() {

        val order = hashMapOf(
            "items" to CartManager.getItems(),
            "totalPrice" to CartManager.getTotal(),
            "status" to "جديد"
        )

        db.collection("orders")
            .add(order)
            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "تم إرسال الطلب بنجاح",
                    Toast.LENGTH_LONG
                ).show()

                CartManager.clear()

                finish()
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "حدث خطأ أثناء إرسال الطلب",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}
