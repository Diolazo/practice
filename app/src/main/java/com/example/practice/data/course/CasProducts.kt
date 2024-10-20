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
import com.example.practice.databinding.CasProductsBinding
import com.example.practice.databinding.CiteProductsBinding

class CasProducts : AppCompatActivity() {
    private lateinit var binding: CasProductsBinding
    private lateinit var departmentAdapter: DepartmentAdapter
    private lateinit var productList: List<Product>
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CasProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.backCas.setOnClickListener {
            finish()
        }

        productList = dbHelper.getAllProducts().filter { it.category == "CAS" }

        departmentAdapter = DepartmentAdapter(this, productList)
        binding.productCAS.apply {
            layoutManager = LinearLayoutManager(this@CasProducts)
            adapter = departmentAdapter
        }

    }
}