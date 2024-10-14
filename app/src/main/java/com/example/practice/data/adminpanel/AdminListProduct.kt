package com.example.practice.data.adminpanel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.databinding.DesignAdminListProductBinding

class AdminListProduct : AppCompatActivity() {
    private lateinit var binding: DesignAdminListProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignAdminListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //productList = arrayListOf(
          //  Product("https://example.com/product1.jpg", "Product 1", "Cite", "20.99")
        //)

        setupRecyclerView()

        binding.btnAdminBack2.setOnClickListener {
            startActivity(Intent(this, AdminDashboard::class.java))
        }
    }

    private fun setupRecyclerView(){
        //productAdapter = ProductAdapter(this, productList)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProduct.adapter
    }

}