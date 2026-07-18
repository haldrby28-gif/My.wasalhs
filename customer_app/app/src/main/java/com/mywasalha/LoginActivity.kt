package com.mywasalha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val phone = findViewById<EditText>(R.id.phoneEditText)
        val button = findViewById<Button>(R.id.continueButton)

        button.setOnClickListener {

            if (phone.text.toString().isEmpty()) {

                Toast.makeText(
                    this,
                    "اكتب رقم الهاتف",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }
}
