package com.mywasalha.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mywasalha.R
import com.mywasalha.models.Restaurant
import com.mywasalha.screens.MenuActivity

class RestaurantAdapter(
    private val restaurants: MutableList<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtRestaurantName: TextView =
            itemView.findViewById(R.id.txtRestaurantName)

        val txtRestaurantAddress: TextView =
            itemView.findViewById(R.id.txtRestaurantAddress)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)

        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RestaurantViewHolder,
        position: Int
    ) {

        val restaurant = restaurants[position]

        holder.txtRestaurantName.text = restaurant.name
        holder.txtRestaurantAddress.text = restaurant.address

        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                MenuActivity::class.java
            )

            intent.putExtra("restaurantId", restaurant.id)
            intent.putExtra("restaurantName", restaurant.name)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }
}
