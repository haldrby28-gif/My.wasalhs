package com.mywasalha.adapters

import com.mywasalha.firebase.CartManager
import com.mywasalha.models.CartItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mywasalha.R
import com.mywasalha.models.Product



class ProductAdapter(

    private val products: MutableList<Product>

) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {



    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        val name: TextView =
            itemView.findViewById(R.id.txtProductName)


        val description: TextView =
            itemView.findViewById(R.id.txtProductDescription)


        val price: TextView =
            itemView.findViewById(R.id.txtProductPrice)


        val addButton: Button =
            itemView.findViewById(R.id.btnAddToCart)

    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {


        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_product,
                    parent,
                    false
                )


        return ProductViewHolder(view)

    }




    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {


        val product =
            products[position]


        holder.name.text =
            product.name


        holder.description.text =
            product.description


        holder.price.text =
            "${product.price} جنيه"



        holder.addButton.setOnClickListener {


            CartManager.addItem(
    CartItem(
        product.id,
        product.name,
        product.price,
        1
    )
)

Toast.makeText(
    holder.itemView.context,
    "تمت إضافة ${product.name} للسلة",
    Toast.LENGTH_SHORT
).show()

            // سنربط السلة الحقيقية لاحقاً

        }


    }



    override fun getItemCount(): Int {

        return products.size

    }

}
