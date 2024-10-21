package com.example.practice.data.course

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice.R
import com.example.practice.databinding.CmaProductsBinding

class CmaProducts : AppCompatActivity() {
    private lateinit var binding: CmaProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CmaProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}