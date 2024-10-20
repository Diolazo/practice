package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice.R
import com.example.practice.databinding.DesignAboutUsAcitivityBinding

class AboutUsAcitivity : AppCompatActivity() {
    private lateinit var binding: DesignAboutUsAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignAboutUsAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAboutBack.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

    }
}