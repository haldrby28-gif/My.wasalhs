package com.waselha.customer.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waselha.customer.R
import com.waselha.customer.models.Restaurant
import com.waselha.customer.screens.MenuActivity

class RestaurantAdapter(
    private val restaurants: List<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.restaurantName)
        val address: TextView = itemView.findViewById(R.id.restaurantAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]

        holder.name.text = restaurant.name
        holder.address.text = restaurant.address

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MenuActivity::class.java)
            intent.putExtra("restaurantId", restaurant.id)
            intent.putExtra("restaurantName", restaurant.name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = restaurants.size
}
