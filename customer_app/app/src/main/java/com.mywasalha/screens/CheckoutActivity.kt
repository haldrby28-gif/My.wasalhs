
package com.mywasalha.screens


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.mywasalha.R
import com.mywasalha.firebase.CartManager



class CheckoutActivity : AppCompatActivity() {


    private lateinit var addressEdit: EditText

    private lateinit var totalText: TextView

    private lateinit var placeOrderButton: Button


    private val db =
        FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_checkout)



        addressEdit =
            findViewById(R.id.edtAddress)


        totalText =
            findViewById(R.id.txtCheckoutTotal)


        placeOrderButton =
            findViewById(R.id.btnPlaceOrder)



        totalText.text =
            "الإجمالي: ${CartManager.getTotal()} جنيه"



        placeOrderButton.setOnClickListener {


            createOrder()

        }

    }




    private fun createOrder() {


        val order = hashMapOf(

            "customerId" to "CURRENT_USER_ID",

            "restaurantId" to "1",

            "address" to addressEdit.text.toString(),

            "totalPrice" to CartManager.getTotal(),

            "status" to "available",

            "driverId" to "",

            "createdAt" to System.currentTimeMillis()

        )



        db.collection("orders")

            .add(order)

            .addOnSuccessListener {


                Toast.makeText(

                    this,

                    "تم إرسال الطلب بنجاح",

                    Toast.LENGTH_SHORT

                ).show()


                CartManager.clearCart()


                finish()

            }


            .addOnFailureListener {


                Toast.makeText(

                    this,

                    "حدث خطأ أثناء إرسال الطلب",

                    Toast.LENGTH_SHORT

                ).show()

            }


    }

}
