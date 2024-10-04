package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.DesignSignInActivityBinding
import com.example.practice.data.HomePageActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: DesignSignInActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignSignInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonForgotLogin.setOnClickListener {
            startActivity(Intent(this, ForgotActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
            //loginUser()
            //Toast.makeText(this, "Logging in", Toast.LENGTH_SHORT).show()
        }

        binding.btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}