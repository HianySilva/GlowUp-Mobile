package com.example.glowup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.glowup.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Categoria"
        binding.tvCategoryTitle.text = categoryName

        binding.btnBack.setOnClickListener { finish() }

        setupRecyclerView(categoryName)
    }

    private fun setupRecyclerView(categoryName: String) {
        val products = listOf(
            Product(name = "Pó Compacto", price = 49.90, imageRes = R.drawable.placeholder),
            Product(name = "Batom Matte", price = 29.90, imageRes = R.drawable.placeholder),
            Product(name = "Rímel à Prova D'água", price = 39.90, imageRes = R.drawable.placeholder),
            Product(name = "Delineador Líquido", price = 34.90, imageRes = R.drawable.placeholder),
            Product(name = "Base Líquida", price = 59.90, imageRes = R.drawable.placeholder),
            Product(name = "Sombra em Pó", price = 24.90, imageRes = R.drawable.placeholder),
            Product(name = "Gloss Labial", price = 19.90, imageRes = R.drawable.placeholder),
            Product(name = "Corretivo", price = 29.90, imageRes = R.drawable.placeholder)
        )

        val adapter = ProductAdapter(products) { product ->
            // Callback quando clicar no botão "Adicionar ao carrinho"
            Toast.makeText(this, "Adicionado: ${product.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }
}
