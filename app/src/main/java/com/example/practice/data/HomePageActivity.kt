package com.example.practice.data

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.Data
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import com.example.practice.data.course.CahsProducts
import com.example.practice.data.course.CasProducts
import com.example.practice.data.course.CcjseProducts
import com.example.practice.data.course.CeaProducts
import com.example.practice.data.course.CelaProducts
import com.example.practice.data.course.CiteProducts
import com.example.practice.data.course.CmaProducts
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.databinding.DesignHomePageActivityBinding

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: DesignHomePageActivityBinding
    private lateinit var dbHelper: DatabaseHelper
    private var doubleBackToExitPressOnce = false
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignHomePageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val userEmail = sharedPreferences.getString("userEmail", null)
        if (userEmail != null){
            val userName = dbHelper.getUserNameByEmail(userEmail)
            binding.tvUser.text = userName?: "Your Name"
        }

        binding.btnCite.setOnClickListener {
            startActivity(Intent(this, CiteProducts::class.java))
        }
        binding.btnCea.setOnClickListener {
            startActivity(Intent(this, CeaProducts::class.java))
        }
        binding.btnCma.setOnClickListener {
            startActivity(Intent(this, CmaProducts::class.java))
        }
        binding.btnCela.setOnClickListener {
            startActivity(Intent(this, CelaProducts::class.java))
        }
        binding.btnCahs.setOnClickListener {
            startActivity(Intent(this, CahsProducts::class.java))
        }
        binding.btnCcjse.setOnClickListener {
            startActivity(Intent(this, CcjseProducts::class.java))
        }
        binding.btnCas.setOnClickListener {
            startActivity(Intent(this, CasProducts::class.java))
        }




        binding.BottomNavMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    if (this::class.java != HomePageActivity::class.java) {
                        startActivity(Intent(this, HomePageActivity::class.java))
                    } else {

                    }
                    true
                }
                R.id.shoppingcart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }
                R.id.settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }else ->false
            }
        }
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
