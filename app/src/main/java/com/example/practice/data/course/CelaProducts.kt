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
import com.example.practice.databinding.CelaProductsBinding
import com.example.practice.databinding.CiteProductsBinding

class CelaProducts : AppCompatActivity() {
    private lateinit var binding: CelaProductsBinding
    private lateinit var departmentAdapter: DepartmentAdapter
    private lateinit var productList: List<Product>
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CelaProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.backCela.setOnClickListener {
            finish()
        }

        productList = dbHelper.getAllProducts().filter { it.category == "CELA" }

        departmentAdapter = DepartmentAdapter(this, productList)
        binding.productCELA.apply {
            layoutManager = LinearLayoutManager(this@CelaProducts)
            adapter = departmentAdapter
        }

    }
}