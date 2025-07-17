package com.example.glowup

import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(), // ID único
    val name: String,
    val price: Double,
    val imageRes: Int,
    val inStock: Boolean = true // Novo campo útil
)