package com.example.glowup

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.glowup.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obter o nome da categoria da intent
        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Categoria"
        binding.tvCategoryTitle.text = categoryName

        // Configurar botão de voltar
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Configurar RecyclerView com produtos da categoria
        setupRecyclerView(categoryName)
    }

    private fun setupRecyclerView(categoryName: String) {
        // Lista de produtos da categoria (exemplo)
        val products = listOf(
            Product("Pó Compacto", 49.90, R.drawable.placeholder),
            Product("Pó Translúcido", 39.90, R.drawable.placeholder),
            Product("Pó Mineral", 59.90, R.drawable.placeholder),
            Product("Pó Matificante", 44.90, R.drawable.placeholder),
            Product("Pó Iluminador", 54.90, R.drawable.placeholder),
            Product("Pó Finalizador", 49.90, R.drawable.placeholder)
        )

        val adapter = ProductAdapter(products)
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }
}