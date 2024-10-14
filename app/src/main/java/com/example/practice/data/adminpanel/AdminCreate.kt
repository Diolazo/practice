package com.example.practice.data.adminpanel

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice.R
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
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 100 && resultCode == Activity.RESULT_OK){
            selectedImageUri = data?.data
            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
            binding.imgProduct.setImageBitmap(bitmap)
        }
    }

    private fun addProduct(){
        val title = binding.etTitle.text.toString().trim()
        val category = binding.spinnerCategory.selectedItem.toString()
        val price = binding.etPrice.text.toString().trim()

        if (selectedImageUri == null){
            Toast.makeText(this, "Please select a product image", Toast.LENGTH_SHORT).show()
        }else if(title.isEmpty()){
            binding.etTitle.error = "Product title is required"
        }else if(price.isEmpty()){
            binding.etPrice.error = "Product price is required"
        }else if(category == "Select Category"){
            Toast.makeText(this, "Please select a valid category", Toast.LENGTH_SHORT).show()
        }else {
            saveProductToDatabase(title, price, category, selectedImageUri!!)
        }
    }
    private fun saveProductToDatabase(title: String, price: String, category: String, imageUri: Uri){

    }

}