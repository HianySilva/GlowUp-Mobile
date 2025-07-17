package com.example.glowup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.glowup.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView com todos os produtos
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Lista de todos os produtos (exemplo)
        val products = listOf(
            Product("Pó Compacto", 49.90, R.drawable.placeholder),
            Product("Batom Matte", 29.90, R.drawable.placeholder),
            Product("Rímel à Prova D'água", 39.90, R.drawable.placeholder),
            Product("Delineador Líquido", 34.90, R.drawable.placeholder),
            Product("Base Líquida", 59.90, R.drawable.placeholder),
            Product("Sombra em Pó", 24.90, R.drawable.placeholder),
            Product("Gloss Labial", 19.90, R.drawable.placeholder),
            Product("Corretivo", 29.90, R.drawable.placeholder)
        )

        val adapter = ProductAdapter(products)
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }
}