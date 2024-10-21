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
            layoutManager = LinearLayoutManager(this@CmaProducts)
            adapter = departmentAdapter
        }

    }
}