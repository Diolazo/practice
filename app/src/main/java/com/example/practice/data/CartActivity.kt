package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.data.course.BuyNowActivity
import com.example.practice.data.files.Cart
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.databinding.DesignCartActivityBinding
import com.example.practice.ui.CartAdapter

class CartActivity : AppCompatActivity() {
    private lateinit var binding: DesignCartActivityBinding
    private lateinit var cartAdapter: CartAdapter
    private val databaseHelper = DatabaseHelper(this)
    private var cartItems: List<Cart> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignCartActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCartBackToHome.setOnClickListener {
            finish()
        }

        setupRecyclerView()
        loadCartItems()
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(cartItems, ::onDeleteItemClick, ::onBuyNowClick)
        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }
    }

    private fun loadCartItems() {
        cartItems = databaseHelper.getCartItems()
        cartAdapter.updateCartItems(cartItems)
    }

    private fun onDeleteItemClick(productId: Int) {
        databaseHelper.deleteCartItem(productId)
        loadCartItems()
    }

    private fun onBuyNowClick(cart: Cart) {
        val intent = Intent(this, BuyNowActivity::class.java)
        intent.putExtra("productId", cart.productId)
        startActivity(intent)

        Toast.makeText(this, "Buying: ${cart.productName}", Toast.LENGTH_SHORT).show()
    }
}
