package com.example.practice.data.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.Product
import com.example.practice.databinding.CiteProductsBinding

class CiteProducts : AppCompatActivity() {
    private lateinit var binding: CiteProductsBinding
    private lateinit var departmentAdapter: DepartmentAdapter
    private lateinit var productList: List<Product>
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CiteProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.backCite.setOnClickListener {
            finish()
        }

        productList = dbHelper.getAllProducts().filter { it.category == "CITE" }

        departmentAdapter = DepartmentAdapter(this, productList)
        binding.productCITE.apply {
            layoutManager = LinearLayoutManager(this@CiteProducts)
            adapter = departmentAdapter
        }

    }
}