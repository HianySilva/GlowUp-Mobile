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
            Product(name = "Pó Compacto", price = 49.90, imageRes = R.drawable.placeholder),
            Product(name = "Pó Translúcido", price = 39.90, imageRes = R.drawable.placeholder),
            Product(name = "Pó Mineral", price = 59.90, imageRes = R.drawable.placeholder),
            Product(name = "Pó Matificante", price = 44.90, imageRes = R.drawable.placeholder),
            Product(name = "Pó Iluminador", price = 54.90, imageRes = R.drawable.placeholder),
            Product(name = "Pó Finalizador", price = 49.90, imageRes = R.drawable.placeholder)
        )

        val adapter = ProductAdapter(products) { product ->
            Toast.makeText(this, "${product.name} adicionado ao carrinho", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }
}
