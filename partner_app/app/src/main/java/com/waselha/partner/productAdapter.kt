package com.waselha.partner

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ProductAdapter(
    private val list: MutableList<Product>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val db = FirebaseFirestore.getInstance()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtName: TextView = itemView.findViewById(R.id.txtProductName)
        val txtPrice: TextView = itemView.findViewById(R.id.txtProductPrice)
        val txtStatus: TextView = itemView.findViewById(R.id.txtProductStatus)

        val btnEdit: Button = itemView.findViewById(R.id.btnEditProduct)
        val btnDelete: Button = itemView.findViewById(R.id.btnDeleteProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = list[position]

        holder.txtName.text = product.name
        holder.txtPrice.text = "السعر: ${product.price} ج.م"

        holder.txtStatus.text =
            if (product.available) "الحالة: متاح"
            else "الحالة: غير متاح"

        holder.btnEdit.setOnClickListener {

            Toast.makeText(
                holder.itemView.context,
                "سيتم إضافة التعديل لاحقًا",
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.btnDelete.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("حذف المنتج")
                .setMessage("هل تريد حذف هذا المنتج؟")
                .setPositiveButton("نعم") { _, _ ->

                    db.collection("products")
                        .document(product.id)
                        .delete()
                        .addOnSuccessListener {

                            val index = holder.adapterPosition

                            if (index != RecyclerView.NO_POSITION) {
                                list.removeAt(index)
                                notifyItemRemoved(index)
                            }

                            Toast.makeText(
                                holder.itemView.context,
                                "تم حذف المنتج",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                .setNegativeButton("إلغاء", null)
                .show()
        }
    }
}
