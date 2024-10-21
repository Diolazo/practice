package com.example.practice.data.course

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.R
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.Product
import com.example.practice.databinding.CeaProductsBinding

class CeaProducts : AppCompatActivity() {
    private lateinit var binding: CeaProductsBinding
    private lateinit var departmentAdapter: DepartmentAdapter
    private lateinit var productList: List<Product>
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CeaProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.backCea.setOnClickListener {
            finish()
        }

        productList = dbHelper.getAllProducts().filter { it.category == "CEA" }

        departmentAdapter = DepartmentAdapter(this, productList)
        binding.productCEA.apply {
            layoutManager = LinearLayoutManager(this@CeaProducts)
            adapter = departmentAdapter
        }
    }
}