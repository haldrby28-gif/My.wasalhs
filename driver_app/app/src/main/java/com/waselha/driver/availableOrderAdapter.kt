package com.mywasalha.driver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class AvailableOrderAdapter(
    private val orders: MutableList<Order>
) : RecyclerView.Adapter<AvailableOrderAdapter.OrderViewHolder>() {


    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val restaurant: TextView =
            itemView.findViewById(R.id.txtRestaurant)

        val address: TextView =
            itemView.findViewById(R.id.txtAddress)

        val price: TextView =
            itemView.findViewById(R.id.txtPrice)

        val accept: Button =
            itemView.findViewById(R.id.btnAccept)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_available_order,
                parent,
                false
            )

        return OrderViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: OrderViewHolder,
        position: Int
    ) {

        val order = orders[position]

        holder.restaurant.text =
            "المطعم: ${order.restaurantName}"

        holder.address.text =
            "العنوان: ${order.address}"

        holder.price.text =
            "الإجمالي: ${order.totalPrice} جنيه"


        holder.accept.setOnClickListener {

            val db = FirebaseFirestore.getInstance()

            db.collection("orders")
                .document(order.id)
                .update(
                    mapOf(
                        "status" to "accepted",
                        "driverId" to "CURRENT_DRIVER_ID"
                    )
                )
                .addOnSuccessListener {

                    orders.removeAt(position)
                    notifyItemRemoved(position)
                }
        }
    }


    override fun getItemCount(): Int {
        return orders.size
    }
}
