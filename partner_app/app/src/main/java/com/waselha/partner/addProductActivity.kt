package com.waselha.partner

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddProductActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_product)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val etName = findViewById<EditText>(R.id.etProductName)
        val etDescription = findViewById<EditText>(R.id.etProductDescription)
        val etPrice = findViewById<EditText>(R.id.etProductPrice)
        val etCategory = findViewById<EditText>(R.id.etCategory)

        val btnSave = findViewById<Button>(R.id.btnSaveProduct)

        btnSave.setOnClickListener {

            val name = etName.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val priceText = etPrice.text.toString().trim()
            val category = etCategory.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "أدخل اسم المنتج"
                return@setOnClickListener
            }

            if (priceText.isEmpty()) {
                etPrice.error = "أدخل السعر"
                return@setOnClickListener
            }

            val uid = auth.currentUser?.uid ?: return@setOnClickListener

            val product = hashMapOf(
                "restaurantId" to uid,
                "categoryId" to category,
                "name" to name,
                "description" to description,
                "price" to priceText.toDouble(),
                "imageUrl" to "",
                "available" to true,
                "createdAt" to System.currentTimeMillis()
            )

            db.collection("products")
                .add(product)
                .addOnSuccessListener {
                    Toast.makeText(this, "تم إضافة المنتج", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "حدث خطأ أثناء الحفظ", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
