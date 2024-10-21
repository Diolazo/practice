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
import com.example.practice.databinding.CiteProductsBinding
import com.example.practice.databinding.CmaProductsBinding

class CmaProducts : AppCompatActivity() {
    private lateinit var binding: CmaProductsBinding
    private lateinit var departmentAdapter: DepartmentAdapter
    private lateinit var productList: List<Product>
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CmaProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.backCma.setOnClickListener {
            finish()
        }

        productList = dbHelper.getAllProducts().filter { it.category == "CMA" }

        departmentAdapter = DepartmentAdapter(this, productList)
        binding.productCMA.apply {
            layoutManager = LinearLayoutManager(this@CmaProducts)
            adapter = departmentAdapter
        }

    }
}