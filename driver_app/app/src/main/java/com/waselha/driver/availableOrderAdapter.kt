package com.mywasalha.driver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class AvailableOrderAdapter(
    private val orders: MutableList<Order>
) : RecyclerView.Adapter<AvailableOrderAdapter.OrderViewHolder>() {


    private val db = FirebaseFirestore.getInstance()


    class OrderViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        val restaurantName: TextView =
            itemView.findViewById(R.id.txtRestaurant)

        val address: TextView =
            itemView.findViewById(R.id.txtAddress)

        val price: TextView =
            itemView.findViewById(R.id.txtPrice)

        val acceptButton: Button =
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


        holder.restaurantName.text =
            "المطعم: ${order.restaurantName}"


        holder.address.text =
            "العنوان: ${order.address}"


        holder.price.text =
            "الإجمالي: ${order.totalPrice} جنيه"



        holder.acceptButton.setOnClickListener {


            val driverId = "CURRENT_DRIVER_ID"


            db.collection("orders")
                .document(order.id)
                .update(
                    mapOf(
                        "status" to "accepted",
                        "driverId" to driverId
                    )
                )
                .addOnSuccessListener {


                    Toast.makeText(
                        holder.itemView.context,
                        "تم قبول الطلب",
                        Toast.LENGTH_SHORT
                    ).show()


                    orders.removeAt(position)

                    notifyItemRemoved(position)

                    notifyItemRangeChanged(
                        position,
                        orders.size
                    )
                }
                .addOnFailureListener {


                    Toast.makeText(
                        holder.itemView.context,
                        "حدث خطأ أثناء قبول الطلب",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }




    override fun getItemCount(): Int {

        return orders.size

    }

}
