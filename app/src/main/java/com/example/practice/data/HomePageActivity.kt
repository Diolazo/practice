package com.example.practice.data

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.DesignHomePageActivityBinding

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: DesignHomePageActivityBinding
    private var doubleBackToExitPressOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DesignHomePageActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)








    }
    override fun onBackPressed() {
        if (doubleBackToExitPressOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressOnce = true
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressOnce = false
        }, 2000)

    }
}