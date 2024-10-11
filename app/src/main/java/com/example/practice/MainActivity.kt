package com.example.practice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.data.SignInActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val delayMillis = 2000L
        android.os.Handler().postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }, delayMillis)

    }
}