package com.mywasalha.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mywasalha.R
import com.mywasalha.models.CartItem

class CartAdapter(
    private val items: List<CartItem>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.cartItemName)
        val quantity: TextView = view.findViewById(R.id.cartItemQuantity)
        val price: TextView = view.findViewById(R.id.cartItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)

        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val item = items[position]

        holder.name.text = item.name
        holder.quantity.text = "الكمية: ${item.quantity}"
        holder.price.text = "السعر: ${item.price * item.quantity} جنيه"
    }

    override fun getItemCount() = items.size
}
