package com.example.practice.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice.data.files.Cart
import com.example.practice.databinding.AddCartItemLayoutBinding

class CartAdapter(
    private var cartItems: List<Cart>,
    private val onDeleteClick: (Int) -> Unit,
    private val onBuyNowClick: (Cart) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: AddCartItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cart: Cart) {
            binding.tvProductName.text = cart.productName
            binding.cbSelectProduct.isChecked = cart.isSelected

            Glide.with(binding.root.context)
                .load(cart.productImageUri)
                .into(binding.ivProductImage)

            binding.btnBuy.setOnClickListener {
                onBuyNowClick(cart)
            }

            binding.btnCartDelete.setOnClickListener {
                showDeleteConfirmationDialog(binding.root.context, cart.productId)
            }
        }
        private fun showDeleteConfirmationDialog(context: Context, productId: Int) {
            AlertDialog.Builder(context).apply {
                setTitle("Delete Item")
                setMessage("Are you sure you want to delete this item?")
                setPositiveButton("Yes") { _, _ ->
                    onDeleteClick(productId)
                    Toast.makeText(context, "Item deleted from cart.", Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("No", null)
                show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = AddCartItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun updateCartItems(newCartItems: List<Cart>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }
}
