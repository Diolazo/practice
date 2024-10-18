package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.ProductListBinding

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.product1.setOnClickListener {
            val intent = Intent(this, CheckActivity::class.java)
            startActivity(intent)
        }
    }

}
