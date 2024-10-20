package com.example.practice.data.course

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice.data.CartActivity
import com.example.practice.data.files.Cart
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.Product
import com.example.practice.databinding.DesignProductBinding

class DepartmentAdapter(private val context: Context, private val productList: List<Product>) : RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>() {

    inner class DepartmentViewHolder(private val binding: DesignProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.tvProductName.text = product.title
            binding.tvProductPrice.text = "₱${product.price}"

            Glide.with(binding.root.context)
                .load(Uri.parse(product.imageUri))
                .into(binding.imgProduct)

            binding.btnBuy.setOnClickListener {
                val intent = Intent(context, BuyNowActivity::class.java)
                context.startActivity(intent)
            }
            binding.btnCart.setOnClickListener {
                addToCart(product)
            }
        }
    }
    private fun addToCart(product: Product) {
        val dbHelper = DatabaseHelper(context)
        val cartItem = Cart(product.id, product.title, product.imageUri)

        val id = dbHelper.addCartItem(cartItem)
        if (id != -1L) {
            Toast.makeText(context, "${product.title} added to cart", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Failed to add to cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val binding = DesignProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DepartmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
