package com.waselha.admin

import com.waselha.admin.R
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class OrderDetailsActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val txtOrderNumber = findViewById<TextView>(R.id.txtOrderNumber)
        val txtCustomer = findViewById<TextView>(R.id.txtCustomer)
        val txtRestaurant = findViewById<TextView>(R.id.txtRestaurant)
        val txtDriver = findViewById<TextView>(R.id.txtDriver)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val txtStatus = findViewById<TextView>(R.id.txtStatus)
        val txtAddress = findViewById<TextView>(R.id.txtAddress)
        val txtNotes = findViewById<TextView>(R.id.txtNotes)

        val btnAccept = findViewById<Button>(R.id.btnAccept)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        val orderId = intent.getStringExtra("orderId") ?: return

        db.collection("orders")
            .document(orderId)
            .get()
            .addOnSuccessListener { document ->

                if (document.exists()) {

                    val order = document.toObject(Order::class.java)

                    order?.let {

                        txtOrderNumber.text = "رقم الطلب: ${it.orderNumber}"
                        txtCustomer.text = "العميل: ${it.customerName}"
                        txtRestaurant.text = "المطعم: ${it.restaurantName}"
                        txtDriver.text = "المندوب: ${it.driverName}"
                        txtTotal.text = "الإجمالي: ${it.totalPrice} ج.م"
                        txtStatus.text = "الحالة: ${it.orderStatus}"
                        txtAddress.text = "العنوان: ${it.deliveryAddress}"
                        txtNotes.text = "ملاحظات: ${it.notes}"
                    }
                }
            }

        btnAccept.setOnClickListener {

            db.collection("orders")
                .document(orderId)
                .update("orderStatus", "ACCEPTED")
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "تم قبول الطلب",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
        }

        btnCancel.setOnClickListener {

            db.collection("orders")
                .document(orderId)
                .update("orderStatus", "CANCELLED")
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "تم إلغاء الطلب",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
        }
    }
}
