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
import com.example.practice.databinding.CahsProductsBinding
import com.example.practice.databinding.CiteProductsBinding

class CahsProducts : AppCompatActivity() {
    private lateinit var binding: CahsProductsBinding
    private lateinit var departmentAdapter: DepartmentAdapter
    private lateinit var productList: List<Product>
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CahsProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.backCahs.setOnClickListener {
            finish()
        }

        productList = dbHelper.getAllProducts().filter { it.category == "CAHS" }

        departmentAdapter = DepartmentAdapter(this, productList)
        binding.productCAHS.apply {
            layoutManager = LinearLayoutManager(this@CahsProducts)
            adapter = departmentAdapter
        }

    }
}