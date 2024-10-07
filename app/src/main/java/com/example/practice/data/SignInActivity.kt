package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.DesignSignInActivityBinding
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.ValidationUtils

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: DesignSignInActivityBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignSignInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.buttonForgotLogin.setOnClickListener {
            startActivity(Intent(this, ForgotActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            loginUser()
            Toast.makeText(this, "Logging in", Toast.LENGTH_SHORT).show()
        }

        binding.btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun loginUser() {
        val email = binding.inputEmailLogin.text.toString().trim()
        val password = binding.inputPasswordLogin.text.toString().trim()

        if (ValidationUtils.isTextNotEmpty(email) && ValidationUtils.isTextNotEmpty(password)) {
            if (ValidationUtils.isValidEmail(email)) {
                val isSuccess = db.loginUser(email, password)
                if (isSuccess) {
                    startActivity(Intent(this, HomePageActivity::class.java))
                    finish()
                }
            } else {
                Toast.makeText(this,"Invalid format email", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,"Please enter all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
