package com.example.glowup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.glowup.api.ApiProduct
import com.example.glowup.api.RetrofitInstance
import com.example.glowup.databinding.ActivityCategoryBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var adapter: ProductApiAdapter
    private val productService = RetrofitInstance.api

    private val categoryMapping = mapOf(
        "Blush" to "blush",
        "Bronzer" to "bronzer",
        "Lápis de Olho" to "eyebrow",
        "Delineador" to "eyeliner",
        "Sombra" to "eyeshadow",
        "Base" to "foundation",
        "Lápis de Boca" to "lip_liner",
        "Batom" to "lipstick",
        "Rímel" to "mascara",
        "Esmalte" to "nail_polish"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Categoria"
        binding.tvCategoryTitle.text = categoryName
        binding.btnBack.setOnClickListener { finish() }

        setupRecyclerView()

        val apiCategory = categoryMapping[categoryName] ?: run {
            Toast.makeText(this, "Categoria não encontrada", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        fetchProducts(apiCategory)
    }

    private fun setupRecyclerView() {
        adapter = ProductApiAdapter(mutableListOf()) { product ->
            // Ao clicar no produto, abrir detalhes passando JSON
            val productJson = Gson().toJson(product)
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product_json", productJson)
            startActivity(intent)
        }
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }

    private fun fetchProducts(category: String) {
        productService.getProducts(category).enqueue(object : Callback<List<ApiProduct>> {
            override fun onResponse(call: Call<List<ApiProduct>>, response: Response<List<ApiProduct>>) {
                if (response.isSuccessful) {
                    val products = response.body() ?: emptyList()
                    adapter.updateProducts(products)
                } else {
                    Toast.makeText(this@CategoryActivity, "Erro ao carregar produtos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ApiProduct>>, t: Throwable) {
                Toast.makeText(this@CategoryActivity, "Falha: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
