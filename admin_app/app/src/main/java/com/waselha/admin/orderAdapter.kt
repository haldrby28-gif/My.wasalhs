package com.waselha.admin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(
    private val list: MutableList<Order>
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtOrderNumber: TextView =
            itemView.findViewById(R.id.txtOrderNumber)

        val txtCustomer: TextView =
            itemView.findViewById(R.id.txtCustomer)

        val txtRestaurant: TextView =
            itemView.findViewById(R.id.txtRestaurant)

        val txtTotal: TextView =
            itemView.findViewById(R.id.txtTotal)

        val txtStatus: TextView =
            itemView.findViewById(R.id.txtStatus)

        val btnDetails: Button =
            itemView.findViewById(R.id.btnOrderDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val order = list[position]

        holder.txtOrderNumber.text = "رقم الطلب: ${order.orderNumber}"
        holder.txtCustomer.text = "العميل: ${order.customerName}"
        holder.txtRestaurant.text = "المطعم: ${order.restaurantName}"
        holder.txtTotal.text = "الإجمالي: ${order.totalPrice} ج.م"
        holder.txtStatus.text = "الحالة: ${order.orderStatus}"

        holder.btnDetails.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                OrderDetailsActivity::class.java
            )

            intent.putExtra("orderId", order.id)

            holder.itemView.context.startActivity(intent)
        }
    }
}
