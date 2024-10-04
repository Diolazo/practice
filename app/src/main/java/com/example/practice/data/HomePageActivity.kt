package com.example.practice.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.DesignHomePageActivityBinding

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: DesignHomePageActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DesignHomePageActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}