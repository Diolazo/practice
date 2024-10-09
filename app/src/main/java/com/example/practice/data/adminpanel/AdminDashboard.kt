package com.example.practice.data.adminpanel

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice.R
import com.example.practice.data.SignInActivity
import com.example.practice.databinding.DesignAdminDashboardBinding

class AdminDashboard : AppCompatActivity() {
    private lateinit var binding: DesignAdminDashboardBinding
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreatep.setOnClickListener {
            startActivity(Intent(this, AdminCreate::class.java))
        }

        binding.btnListp.setOnClickListener {
            startActivity(Intent(this, AdminListProduct::class.java))
        }

        binding.btnListu.setOnClickListener {
            startActivity(Intent(this, AdminListUser::class.java))
        }

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)

    }
}