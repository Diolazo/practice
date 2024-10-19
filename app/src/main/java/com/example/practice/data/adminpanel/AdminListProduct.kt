package com.example.practice.data.adminpanel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.Product
import com.example.practice.databinding.DesignAdminListProductBinding
import androidx.appcompat.app.AlertDialog

class AdminListProduct : AppCompatActivity() {
    private lateinit var binding: DesignAdminListProductBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private var productList: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignAdminListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        setupRecyclerView()
        loadProducts()

        binding.btnAdminBack2.setOnClickListener {
            startActivity(Intent(this, AdminDashboard::class.java))
        }
    }

    private fun loadProducts() {
        productList = databaseHelper.getAllProducts()
        productAdapter.updateProducts(productList)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList) { product -> onProductClick(product) }
        binding.recyclerViewProduct.adapter = productAdapter
    }

    private fun onProductClick(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete ${product.title}?")
            .setPositiveButton("Yes") { _, _ -> deleteProduct(product) }
            .setNegativeButton("No", null)
            .show()
    }

    private fun deleteProduct(product: Product) {
        val deletedRows = databaseHelper.deleteProduct(product.id)
        if (deletedRows > 0) {
            Toast.makeText(this, "Product deleted successfully", Toast.LENGTH_SHORT).show()
            loadProducts()
        } else {
            Toast.makeText(this, "Failed to delete product", Toast.LENGTH_SHORT).show()
        }
    }
}
