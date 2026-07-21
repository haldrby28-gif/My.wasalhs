package com.waselha.driver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AvailableOrderAdapter(
    private val list: MutableList<Order>
) : RecyclerView.Adapter<AvailableOrderAdapter.ViewHolder>() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtRestaurant: TextView =
            itemView.findViewById(R.id.txtRestaurant)

        val txtCustomer: TextView =
            itemView.findViewById(R.id.txtCustomer)

        val txtTotal: TextView =
            itemView.findViewById(R.id.txtTotal)

        val txtAddress: TextView =
            itemView.findViewById(R.id.txtAddress)

        val btnAccept: Button =
            itemView.findViewById(R.id.btnAcceptOrder)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_available_order, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val order = list[position]

        holder.txtRestaurant.text =
            "المطعم: ${order.restaurantName}"

        holder.txtCustomer.text =
            "العميل: ${order.customerName}"

        holder.txtTotal.text =
            "الإجمالي: ${order.totalPrice} ج.م"

        holder.txtAddress.text =
            "العنوان: ${order.deliveryAddress}"


        holder.btnAccept.setOnClickListener {

            val uid = auth.currentUser?.uid ?: return@setOnClickListener

            db.collection("orders")
                .document(order.id)
                .update(
                    mapOf(
                        "driverId" to uid,
                        "driverName" to "مندوب",
                        "orderStatus" to "PICKED_UP"
                    )
                )
                .addOnSuccessListener {

                    Toast.makeText(
                        holder.itemView.context,
                        "تم استلام الطلب",
                        Toast.LENGTH_SHORT
                    ).show()

                    list.removeAt(holder.adapterPosition)
                    notifyItemRemoved(holder.adapterPosition)
                }
        }
    }
}
