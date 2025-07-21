// ProductsActivity.kt
package com.example.glowup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.glowup.api.ApiProduct
import com.example.glowup.api.RetrofitInstance
import com.example.glowup.databinding.ActivityProductsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private lateinit var adapter: ProductApiAdapter
    private val productService = RetrofitInstance.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }
        setupRecyclerView()
        fetchAllProducts()
    }

    private fun setupRecyclerView() {
        adapter = ProductApiAdapter(mutableListOf()) { product ->
            Toast.makeText(this, "${product.name ?: "Produto"} adicionado ao carrinho", Toast.LENGTH_SHORT).show()
        }
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }

    private fun fetchAllProducts() {
        productService.getProducts().enqueue(object : Callback<List<ApiProduct>> {
            override fun onResponse(call: Call<List<ApiProduct>>, response: Response<List<ApiProduct>>) {
                if (response.isSuccessful) {
                    val products = response.body() ?: emptyList()
                    adapter.updateProducts(products)
                } else {
                    Toast.makeText(this@ProductsActivity, "Erro ao carregar produtos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ApiProduct>>, t: Throwable) {
                Toast.makeText(this@ProductsActivity, "Falha: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}