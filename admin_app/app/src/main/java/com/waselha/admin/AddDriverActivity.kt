package com.waselha.admin

import com.waselha.admin.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddDriverActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_driver)

        db = FirebaseFirestore.getInstance()

        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etCity = findViewById<EditText>(R.id.etCity)
        val etVehicleType = findViewById<EditText>(R.id.etVehicleType)
        val etVehicleNumber = findViewById<EditText>(R.id.etVehicleNumber)

        val btnSave = findViewById<Button>(R.id.btnSaveDriver)

        btnSave.setOnClickListener {

            val fullName = etFullName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val city = etCity.text.toString().trim()
            val vehicleType = etVehicleType.text.toString().trim()
            val vehicleNumber = etVehicleNumber.text.toString().trim()

            if (fullName.isEmpty()) {
                etFullName.error = "أدخل اسم المندوب"
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                etPhone.error = "أدخل رقم الهاتف"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                etEmail.error = "أدخل البريد الإلكتروني"
                return@setOnClickListener
            }

            if (city.isEmpty()) {
                etCity.error = "أدخل المدينة"
                return@setOnClickListener
            }

            val driver = hashMapOf(
                "fullName" to fullName,
                "phone" to phone,
                "email" to email,
                "city" to city,
                "vehicleType" to vehicleType,
                "vehicleNumber" to vehicleNumber,
                "active" to true,
                "available" to false,
                "uid" to "",
                "createdAt" to System.currentTimeMillis()
            )

            db.collection("drivers")
                .add(driver)
                .addOnSuccessListener {
                    Toast.makeText(this, "تم إضافة المندوب", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "فشل إضافة المندوب", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
