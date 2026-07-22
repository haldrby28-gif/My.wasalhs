
package com.mywasalha.screens


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mywasalha.R
import com.mywasalha.adapters.ProductAdapter
import com.mywasalha.models.Product


class MenuActivity : AppCompatActivity() {


    private lateinit var restaurantName: TextView

    private lateinit var recyclerView: RecyclerView

    private lateinit var cartButton: Button


    private lateinit var adapter: ProductAdapter


    private val products =
        mutableListOf<Product>()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)



        restaurantName =
            findViewById(R.id.txtRestaurantName)



        recyclerView =
            findViewById(R.id.productsRecyclerView)



        cartButton =
            findViewById(R.id.btnGoToCart)



        recyclerView.layoutManager =
            LinearLayoutManager(this)



        adapter =
            ProductAdapter(products)



        recyclerView.adapter =
            adapter



        loadProducts()



        cartButton.setOnClickListener {


            startActivity(
                Intent(
                    this,
                    CartActivity::class.java
                )
            )

        }

    }



    private fun loadProducts() {


        // بيانات تجريبية مؤقتة
        // سيتم استبدالها بـ Firebase


        products.add(

            Product(
                "1",
                "وجبة برجر",
                "برجر + بطاطس",
                80.0,
                "1"
            )

        )


        products.add(

            Product(
                "2",
                "بيتزا",
                "بيتزا خضار",
                120.0,
                "1"
            )

        )


        restaurantName.text =
            "مطعم تجريبي"


        adapter.notifyDataSetChanged()

    }

}
