package com.example.practice.data.adminpanel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.Product
import com.example.practice.databinding.DesignAdminListProductBinding

class AdminListProduct : AppCompatActivity() {
    private lateinit var binding: DesignAdminListProductBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var productList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignAdminListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        productList = databaseHelper.getAllProducts()

        setupRecyclerView()

        binding.btnAdminBack2.setOnClickListener {
            startActivity(Intent(this, AdminDashboard::class.java))
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList) { product ->
            deleteProduct(product)
        }
        binding.recyclerViewProduct.adapter = productAdapter
    }

    private fun deleteProduct(product: Product) {
        val result = databaseHelper.deleteProduct(product.id)
        if (result > 0) {
            Toast.makeText(this, "${product.title} deleted", Toast.LENGTH_SHORT).show()

            productList = databaseHelper.getAllProducts()
            productAdapter = ProductAdapter(productList) { product ->
                deleteProduct(product)
            }
            binding.recyclerViewProduct.adapter = productAdapter
        } else {
            Toast.makeText(this, "Failed to delete ${product.title}", Toast.LENGTH_SHORT).show()
        }
    }
}
