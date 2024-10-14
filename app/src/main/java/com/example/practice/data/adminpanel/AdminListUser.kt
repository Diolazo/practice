package com.example.practice.data.adminpanel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice.R
import com.example.practice.databinding.DesignAdminListUserBinding

class AdminListUser : AppCompatActivity() {
    private lateinit var binding: DesignAdminListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignAdminListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}