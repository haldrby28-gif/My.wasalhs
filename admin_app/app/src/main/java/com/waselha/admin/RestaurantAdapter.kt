package com.waselha.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(
    private val list: List<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.txtName)
        val owner = itemView.findViewById<TextView>(R.id.txtOwner)
        val phone = itemView.findViewById<TextView>(R.id.txtPhone)
        val city = itemView.findViewById<TextView>(R.id.txtCity)

        val edit = itemView.findViewById<Button>(R.id.btnEdit)
        val delete = itemView.findViewById<Button>(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val restaurant = list[position]

        holder.name.text = restaurant.restaurantName
        holder.owner.text = "المالك: ${restaurant.ownerName}"
        holder.phone.text = "الهاتف: ${restaurant.phone}"
        holder.city.text = "المدينة: ${restaurant.city}"

        holder.edit.setOnClickListener {
            // سنضيف التعديل لاحقًا
        }

        holder.delete.setOnClickListener {
            // سنضيف الحذف لاحقًا
        }

    }

    override fun getItemCount(): Int = list.size
}
