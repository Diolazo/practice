package com.example.practice.data.adminpanel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice.data.files.Product
import com.example.practice.databinding.AdminListProductBinding

class ProductAdapter(private var productList: List<Product>, private val onDeleteClick: (Product) -> Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(val binding: AdminListProductBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = AdminListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.binding.tvProductName.text = product.title
        holder.binding.tvProductPrice.text = "₱${product.price}"
        holder.binding.tvProductCategory.text =product.category
        Glide.with(holder.binding.imgProduct.context)
            .load(product.imageUri)
            .into(holder.binding.imgProduct)

        holder.binding.btnDelete2.setOnClickListener {
            onDeleteClick(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}