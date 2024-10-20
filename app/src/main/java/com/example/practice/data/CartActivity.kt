package com.example.practice.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import com.example.practice.databinding.DesignCartActivityBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: DesignCartActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignCartActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCartBackToHome.setOnClickListener {
            finish()
        }



    }
}