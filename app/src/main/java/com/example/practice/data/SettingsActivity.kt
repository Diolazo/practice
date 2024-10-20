package com.example.practice.data

import android.content.Intent
import android.os.Bundle
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.databinding.DesignSettingsActivityBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: DesignSettingsActivityBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignSettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val userEmail = sharedPreferences.getString("userEmail", null)
        if (userEmail != null){
            val userName = dbHelper.getUserNameByEmail(userEmail)
            binding.tvUsername.text = userName?: "Your Name"
        }




        binding.btnSettingsBack.setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
        }
        binding.btnSettingsAboutUs.setOnClickListener {
            startActivity(Intent(this, AboutUsAcitivity::class.java))
        }
        binding.btnSettingsLogOut.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Are you sure you want to log out?")
            builder.setPositiveButton("Yes") { dialog, _ ->
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

    }
}