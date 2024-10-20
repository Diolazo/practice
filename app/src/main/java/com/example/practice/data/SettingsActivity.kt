package com.example.practice.data

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.practice.R
import com.example.practice.databinding.DesignSettingsActivityBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: DesignSettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DesignSettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}