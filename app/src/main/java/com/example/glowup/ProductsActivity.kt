package com.example.glowup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.glowup.databinding.ActivityProductsBinding
import android.widget.Toast


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
            Toast.makeText(this, "${product.name} adicionado ao carrinho", Toast.LENGTH_SHORT).show()
        }
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }
}