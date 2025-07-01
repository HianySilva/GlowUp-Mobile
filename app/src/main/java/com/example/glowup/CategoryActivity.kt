package com.example.glowup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Categoria"
        title = categoryName

        // Implementação dos produtos por categoria
    }
}