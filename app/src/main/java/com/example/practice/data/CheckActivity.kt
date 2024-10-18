package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice.R
import com.example.practice.databinding.CheckoutItemBinding // Ensure you have the correct binding class

abstract class CheckActivity : AppCompatActivity() {

    private lateinit var binding: CheckoutItemBinding // Use the correct binding type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the binding layout
        binding = CheckoutItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle window insets for edge-to-edge support
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up click listeners
        binding.goBack.setOnClickListener {
            onBackPressed() // Go back to the previous activity
        }

        binding.buyNow.setOnClickListener {
            startActivity(Intent(this, BuyNowActivity::class.java)) // Navigate to Buy Now Activity
        }
    }
}
