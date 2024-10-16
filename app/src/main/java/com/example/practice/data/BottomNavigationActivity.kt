package com.example.practice.data

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.practice.R
import com.example.practice.databinding.BottomNavigationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: BottomNavigationBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var shoppingCartFragment: ShoppingCartFragment
    private lateinit var settingsFragment: SettingsFragment
    private var doubleBackToExitPressOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavMenu = binding.BottomNavMenu
        val frameLayout = binding.frameLayout

        homeFragment = HomeFragment()
        shoppingCartFragment = ShoppingCartFragment()
        settingsFragment = SettingsFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottomNavMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.shoppingcart -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, shoppingCartFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.settings -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, settingsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
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
