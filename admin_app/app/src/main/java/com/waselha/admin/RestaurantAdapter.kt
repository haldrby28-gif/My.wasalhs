package com.waselha.admin

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantAdapter(
    private val list: MutableList<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    private val db = FirebaseFirestore.getInstance()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtOwner: TextView = itemView.findViewById(R.id.txtOwner)
        val txtPhone: TextView = itemView.findViewById(R.id.txtPhone)
        val txtCity: TextView = itemView.findViewById(R.id.txtCity)

        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val restaurant = list[position]

        holder.txtName.text = restaurant.restaurantName
        holder.txtOwner.text = "المالك: ${restaurant.ownerName}"
        holder.txtPhone.text = "الهاتف: ${restaurant.phone}"
        holder.txtCity.text = "المدينة: ${restaurant.city}"

        holder.btnEdit.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "سيتم إضافة التعديل في الخطوة القادمة",
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.btnDelete.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("حذف المطعم")
                .setMessage("هل تريد حذف هذا المطعم؟")
                .setPositiveButton("نعم") { _, _ ->

                    db.collection("restaurants")
                        .document(restaurant.id)
                        .delete()
                        .addOnSuccessListener {

                            val index = holder.adapterPosition
                            if (index != RecyclerView.NO_POSITION) {
                                list.removeAt(index)
                                notifyItemRemoved(index)
                            }

                            Toast.makeText(
                                holder.itemView.context,
                                "تم حذف المطعم",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        .addOnFailureListener {

                            Toast.makeText(
                                holder.itemView.context,
                                "فشل حذف المطعم",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                }
                .setNegativeButton("إلغاء", null)
                .show()
        }
    }
}
