package com.example.glowup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.glowup.databinding.ActivityCategoryBinding

class LipstickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = "Batons"
        binding.tvCategoryTitle.text = categoryName

        binding.btnBack.setOnClickListener { finish() }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val products = listOf(
            Product(name = "Batom Matte Nude", price = 29.90, imageRes = R.drawable.placeholder),
            Product(name = "Batom Vermelho Intenso", price = 34.90, imageRes = R.drawable.placeholder),
            Product(name = "Batom Hidratante Rosa", price = 31.90, imageRes = R.drawable.placeholder),
            Product(name = "Batom Glossy Coral", price = 27.90, imageRes = R.drawable.placeholder)
        )

        val adapter = ProductAdapter(products) { product ->
            Toast.makeText(this, "Adicionado: ${product.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }
}
