package com.example.practice.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.data.files.ShoppingCartItem

class ShoppingCartAdapter(private val items: List<ShoppingCartItem>) : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
//        val itemName: TextView = itemView.findViewById(R.id.itemName)
//        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)

        fun bind(item: ShoppingCartItem) {
            checkBox.isChecked = item.isChecked
//            itemName.text = item.name
//            itemPrice.text = "$${item.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_cart_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
//            item.isChecked = isChecked
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}