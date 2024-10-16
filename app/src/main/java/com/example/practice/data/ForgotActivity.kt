package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.DesignForgotActivityBinding

class ForgotActivity : AppCompatActivity() {
    private lateinit var binding: DesignForgotActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignForgotActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnResetPassword.setOnClickListener {
            val email = binding.inputEmailForgot.text.toString()

            if (email.isEmpty()&& android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Reset instructions sent to $email", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBackToLogin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

    }
}