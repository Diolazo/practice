package com.example.practice.data.adminpanel

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.practice.R
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.Product
import com.example.practice.databinding.DesignAdminCreateBinding

class AdminCreate : AppCompatActivity() {
    private lateinit var binding: DesignAdminCreateBinding
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignAdminCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categories = arrayOf("CITE", "CEA", "CMA", "CELA", "CAHS", "CCJSE", "CAS", "SHS")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        binding.spinnerCategory.adapter = adapter

        binding.btnCreate.setOnClickListener {
            addProduct()
        }

        binding.imgProduct.setOnClickListener {
            chooseImage()
        }

        binding.btnAdminBack.setOnClickListener {
            startActivity(Intent(this, AdminDashboard::class.java))
        }
    }

    private fun chooseImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 100)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseImage()
            } else {
                Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            if (selectedImageUri != null) {
                Glide.with(this)
                    .load(selectedImageUri)
                    .into(binding.imgProduct)
            } else {
                Toast.makeText(this, "Error: Unable to select image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addProduct() {
        val title = binding.etTitle.text.toString().trim()
        val category = binding.spinnerCategory.selectedItem.toString()
        val price = binding.etPrice.text.toString().trim()

        if (selectedImageUri == null) {
            Toast.makeText(this, "Please select a product image", Toast.LENGTH_SHORT).show()
        } else if (title.isEmpty()) {
            binding.etTitle.error = "Product title is required"
        } else if (price.isEmpty()) {
            binding.etPrice.error = "Product price is required"
        } else {
            saveProductToDatabase(title, price, category, selectedImageUri!!)
        }
    }

    private fun saveProductToDatabase(title: String, price: String, category: String, imageUri: Uri) {
        val dbHelper = DatabaseHelper(this)
        val newProduct = Product(title = title, price = price, category = category, imageUri = imageUri.toString())
        dbHelper.addProduct(newProduct)
        Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, AdminListProduct::class.java))
    }
}
