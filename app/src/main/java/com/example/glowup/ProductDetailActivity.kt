package com.example.glowup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.glowup.api.ApiProduct
import com.example.glowup.databinding.ActivityProductDetailBinding
import com.google.gson.Gson

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receber o JSON do produto enviado pela Intent
        val productJson = intent.getStringExtra("product_json")
        if (productJson != null) {
            val product = Gson().fromJson(productJson, ApiProduct::class.java)
            showProductDetails(product)
        } else {
            finish() // fecha se não vier produto
        }

        binding.btnBuy.setOnClickListener {
            // Compra ilustrativa, só um toast por enquanto
            Toast.makeText(this, "Compra realizada (simulada)!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProductDetails(product: ApiProduct) {
        // Ajustar URL da imagem
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

        binding.tvDescriptionDetail.text = product.description?.let { android.text.Html.fromHtml(it) } ?: "Descrição indisponível"
    }
}
