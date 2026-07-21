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


        val onWayButton: Button =
            itemView.findViewById(R.id.btnOnWay)


        val deliveredButton: Button =
            itemView.findViewById(R.id.btnDelivered)

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



        // قبول الطلب

        holder.acceptButton.setOnClickListener {


            updateStatus(
                order.id,
                "accepted",
                holder
            )

        }



        // خرج للتوصيل

        holder.onWayButton.setOnClickListener {


            updateStatus(
                order.id,
                "on_the_way",
                holder
            )

        }



        // تم التسليم

        holder.deliveredButton.setOnClickListener {


            updateStatus(
                order.id,
                "delivered",
                holder
            )


        }


    }



    private fun updateStatus(
        orderId: String,
        status: String,
        holder: OrderViewHolder
    ) {


        db.collection("orders")
            .document(orderId)
            .update(
                "status",
                status
            )
            .addOnSuccessListener {


                Toast.makeText(
                    holder.itemView.context,
                    "تم تحديث حالة الطلب",
                    Toast.LENGTH_SHORT
                ).show()


            }
            .addOnFailureListener {


                Toast.makeText(
                    holder.itemView.context,
                    "حدث خطأ",
                    Toast.LENGTH_SHORT
                ).show()


            }

    }




    override fun getItemCount(): Int {

        return orders.size

    }

}
