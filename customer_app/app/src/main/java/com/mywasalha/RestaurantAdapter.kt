package com.mywasalha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(
    private val restaurantList: ArrayList<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {


    class RestaurantViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.restaurantName)
        val phone: TextView = itemView.findViewById(R.id.restaurantPhone)
        val address: TextView = itemView.findViewById(R.id.restaurantAddress)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item, parent, false)

        return RestaurantViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: RestaurantViewHolder,
        position: Int
    ) {

        val restaurant = restaurantList[position]

        holder.name.text = restaurant.name
        holder.phone.text = restaurant.phone
        holder.address.text = restaurant.address
    }


    override fun getItemCount(): Int {
        return restaurantList.size
    }
}
