package com.waselha.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val email = findViewById<EditText>(R.id.emailEditText)
        val password = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {

            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "أدخل البريد الإلكتروني وكلمة المرور", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnSuccessListener {

                    val uid = auth.currentUser!!.uid

                    db.collection("users")
                        .document(uid)
                        .get()
                        .addOnSuccessListener { document ->

                            val role = document.getString("role")
                            val active = document.getBoolean("active") ?: false

                            if (role == "super_admin" && active) {

                                startActivity(
                                    Intent(this, AdminDashboardActivity::class.java)
                                )

                                finish()

                            } else {

                                auth.signOut()

                                Toast.makeText(
                                    this,
                                    "ليس لديك صلاحية الدخول",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }

                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "فشل تسجيل الدخول",
                        Toast.LENGTH_LONG
                    ).show()

                }

        }

    }
}
