package com.mywasalha.screens

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.mywasalha.R

class OrderTrackingActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var backButton: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_tracking)

        statusText = findViewById(R.id.txtOrderStatus)
        backButton = findViewById(R.id.btnBackHome)

        val orderId = intent.getStringExtra("orderId")

        if (orderId != null) {
            trackOrder(orderId)
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun trackOrder(orderId: String) {
        db.collection("orders")
            .document(orderId)
            .addSnapshotListener { snapshot: DocumentSnapshot?, error: FirebaseFirestoreException? ->
                if (snapshot != null && snapshot.exists()) {
                    val status = snapshot.getString("status")

                    statusText.text = when(status) {
                        "available" -> "جاري تجهيز الطلب"
                        "accepted" -> "السائق استلم الطلب"
                        "on_the_way" -> "السائق في الطريق"
                        "delivered" -> "تم تسليم الطلب"
                        else -> "حالة غير معروفة"
                    }
                }
            }
    }
}
