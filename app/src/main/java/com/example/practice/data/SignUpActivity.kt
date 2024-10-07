package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.Users
import com.example.practice.data.files.ValidationUtils
import com.example.practice.databinding.DesignSignUpActivityBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: DesignSignUpActivityBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignSignUpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnSignup.setOnClickListener {
            registerUser()
        }

        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun registerUser() {
        val name = binding.inputNameSignup.text.toString()
        val email = binding.inputEmailSingup.text.toString()
        val password = binding.inputPasswordSingup.text.toString()
        val confirm = binding.inputRetypeSignup.text.toString()

        if (ValidationUtils.isTextNotEmpty(name) &&
            ValidationUtils.isTextNotEmpty(email) &&
            ValidationUtils.isTextNotEmpty(password) &&
            ValidationUtils.isTextNotEmpty(confirm)
        ) {
            if (ValidationUtils.isValidEmail(email)) {
                if (password == confirm) {
                    val user = Users(name = name, email = email.trim(), password = password, confirmPassword = confirm)
                    db.registerUser(user)
                    Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please input all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
