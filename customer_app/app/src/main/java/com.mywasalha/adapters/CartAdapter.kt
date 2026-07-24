package com.waselha.customer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waselha.customer.R
import com.waselha.customer.firebase.CartManager
import com.waselha.customer.models.CartItem

class CartAdapter(
    private val items: MutableList<CartItem>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val name: TextView =
            itemView.findViewById(R.id.txtCartProductName)

        val quantity: TextView =
            itemView.findViewById(R.id.txtCartQuantity)

        val price: TextView =
            itemView.findViewById(R.id.txtCartPrice)

        val btnDelete: ImageButton =
            itemView.findViewById(R.id.btnDeleteItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)

        return CartViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {

        val item = items[position]

        holder.name.text = item.name
        holder.quantity.text = "الكمية: ${item.quantity}"
        holder.price.text = "${item.price * item.quantity} جنيه"

        holder.btnDelete.setOnClickListener {

            CartManager.removeItem(item.id)

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
