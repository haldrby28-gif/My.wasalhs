package com.mywasalha.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mywasalha.R
import com.mywasalha.models.Restaurant

class RestaurantAdapter(
    private val restaurantList: List<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.restaurantName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.name.text = restaurantList[position].name
    }

    override fun getItemCount(): Int = restaurantList.size
}
