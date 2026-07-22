package com.mywasalha.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.mywasalha.R

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        emailEdit = findViewById(R.id.edtEmail)
        passwordEdit = findViewById(R.id.edtPassword)
        loginButton = findViewById(R.id.btnLogin)
        registerButton = findViewById(R.id.btnRegister)

        loginButton.setOnClickListener {
            login()
        }

        registerButton.setOnClickListener {
            register()
        }
    }

    private fun login() {

        val email = emailEdit.text.toString().trim()
        val password = passwordEdit.text.toString().trim()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                startActivity(
                    Intent(this, MainActivity::class.java)
                )

                finish()
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "فشل تسجيل الدخول",
                    Toast.LENGTH_SHORT
                ).show()

            }

    }

    private fun register() {

        val email = emailEdit.text.toString().trim()
        val password = passwordEdit.text.toString().trim()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "تم إنشاء الحساب",
                    Toast.LENGTH_SHORT
                ).show()

            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "تعذر إنشاء الحساب",
                    Toast.LENGTH_SHORT
                ).show()

            }

    }
}
