package com.example.glowup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.glowup.api.ApiProduct
import com.example.glowup.databinding.ActivityProductDetailBinding
import com.example.glowup.CartManager
import com.google.gson.Gson

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var currentProduct: ApiProduct? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productJson = intent.getStringExtra("product_json")
        if (productJson != null) {
            val product = Gson().fromJson(productJson, ApiProduct::class.java)
            currentProduct = product
            showProductDetails(product)
        } else {
            finish()
        }

        binding.btnBuy.setOnClickListener {
            currentProduct?.let { product ->
                val added = CartManager.addToCart(this, product)
                if (added) {
                    Toast.makeText(this, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Produto já está no carrinho.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun showProductDetails(product: ApiProduct) {
        val imageUrl = product.imageUrl?.let {
            if (it.startsWith("//")) "https:$it" else it
        } ?: ""

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.imgProductDetail)

        binding.tvProductNameDetail.text = product.name ?: "Sem nome"
        binding.tvBrandDetail.text = product.brand ?: "Marca indisponível"
        binding.tvCategoryDetail.text = product.category ?: "Categoria indisponível"

        val priceText = product.price?.toDoubleOrNull()?.let { "R$ %.2f".format(it) } ?: "Preço indisponível"
        binding.tvPriceDetail.text = priceText

        binding.tvDescriptionDetail.text = product.description?.let {
            android.text.Html.fromHtml(it)
        } ?: "Descrição indisponível"
    }
}
