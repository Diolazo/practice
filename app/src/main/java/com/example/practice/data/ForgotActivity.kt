package com.example.practice.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.DesignForgotActivityBinding

class ForgotActivity : AppCompatActivity() {
    private lateinit var binding: DesignForgotActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignForgotActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}