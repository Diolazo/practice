package com.example.practice.data.files

data class Cart(
    val productId: Int,
    val productName: String,
    val productImageUri: String,
    var isSelected: Boolean = false
)
