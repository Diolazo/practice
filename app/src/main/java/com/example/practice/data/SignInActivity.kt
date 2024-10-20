package com.example.practice.data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.data.adminpanel.AdminDashboard
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

        showUserGuide()

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

    private fun showUserGuide(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("User Guide")
        builder.setMessage("Welcome to the login page! Please enter your credentials to log in. If you don't have an account, click on 'Create Account'.")
        builder.setPositiveButton("OK"){dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun loginUser() {
        val email = binding.inputEmailLogin.text.toString().trim()
        val password = binding.inputPasswordLogin.text.toString().trim()

        binding.inputEmailLogin.error = null
        binding.inputPasswordLogin.error = null

        if (ValidationUtils.isTextNotEmpty(email) && ValidationUtils.isTextNotEmpty(password)) {
            if (ValidationUtils.isValidEmail(email)) {
                CoroutineScope(Dispatchers.IO).launch {
                    val isSuccess = db.loginUser(email, password)

                    withContext(Dispatchers.Main) {
                        if (isSuccess) {
                            val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("userEmail", email)
                            editor.apply()

                            if (email == "admin4@gmail.com") {
                                startActivity(Intent(this@SignInActivity, AdminDashboard::class.java))
                            } else {
                                startActivity(Intent(this@SignInActivity, HomePageActivity::class.java))
                            }
                        } else {
                            binding.inputPasswordLogin.error = "Invalid email or password"
                        }
                    }
                }
            } else {
                binding.inputEmailLogin.error = "Invalid email format"
            }
        } else {
            if (email.isEmpty()) {
                binding.inputEmailLogin.error = "Please enter your email"
            }
            if (password.isEmpty()) {
                binding.inputPasswordLogin.error = "Please enter your password"
            }
        }
    }

}
