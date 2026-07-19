package com.waselha.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(
    private val restaurants: List<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.txtRestaurantName)
        val owner: TextView = view.findViewById(R.id.txtOwner)
        val city: TextView = view.findViewById(R.id.txtCity)

        val edit: Button = view.findViewById(R.id.btnEdit)
        val delete: Button = view.findViewById(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val restaurant = restaurants[position]

        holder.name.text = restaurant.restaurantName
        holder.owner.text = "المالك : ${restaurant.ownerName}"
        holder.city.text = "المدينة : ${restaurant.city}"

        holder.edit.setOnClickListener {
            // سنضيف شاشة التعديل لاحقًا
        }

        holder.delete.setOnClickListener {
            // سنضيف الحذف لاحقًا
        }

    }

    override fun getItemCount(): Int = restaurants.size

}
