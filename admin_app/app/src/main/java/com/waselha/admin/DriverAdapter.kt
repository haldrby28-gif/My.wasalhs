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

class DriverAdapter(
    private val list: MutableList<Driver>
) : RecyclerView.Adapter<DriverAdapter.ViewHolder>() {

    private val db = FirebaseFirestore.getInstance()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtName: TextView = itemView.findViewById(R.id.txtDriverName)
        val txtPhone: TextView = itemView.findViewById(R.id.txtDriverPhone)
        val txtCity: TextView = itemView.findViewById(R.id.txtDriverCity)

        val btnEdit: Button = itemView.findViewById(R.id.btnEditDriver)
        val btnDelete: Button = itemView.findViewById(R.id.btnDeleteDriver)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_driver, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val driver = list[position]

        holder.txtName.text = driver.fullName
        holder.txtPhone.text = "الهاتف: ${driver.phone}"
        holder.txtCity.text = "المدينة: ${driver.city}"

        holder.btnEdit.setOnClickListener {

            Toast.makeText(
                holder.itemView.context,
                "سيتم إضافة التعديل لاحقًا",
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.btnDelete.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("حذف المندوب")
                .setMessage("هل تريد حذف هذا المندوب؟")
                .setPositiveButton("نعم") { _, _ ->

                    db.collection("drivers")
                        .document(driver.id)
                        .delete()
                        .addOnSuccessListener {

                            val index = holder.adapterPosition

                            if (index != RecyclerView.NO_POSITION) {
                                list.removeAt(index)
                                notifyItemRemoved(index)
                            }

                            Toast.makeText(
                                holder.itemView.context,
                                "تم حذف المندوب",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                .setNegativeButton("إلغاء", null)
                .show()
        }
    }
}
