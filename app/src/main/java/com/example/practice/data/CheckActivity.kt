package com.example.practice.data

import BuyNowActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice.databinding.DesignCheckoutItemBinding

abstract class CheckActivity : AppCompatActivity() {

    private lateinit var binding: DesignCheckoutItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignCheckoutItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.goBack.setOnClickListener {
            onBackPressed() // Go back to the previous activity
        }

        binding.buyNow.setOnClickListener {
            startActivity(Intent(this, BuyNowActivity::class.java)) // Navigate to Buy Now Activity
        }
    }
}
