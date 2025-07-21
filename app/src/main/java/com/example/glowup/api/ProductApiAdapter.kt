// ProductApiAdapter.kt
package com.example.glowup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.glowup.api.ApiProduct
import com.example.glowup.databinding.ItemProductBinding

class ProductApiAdapter(
    private val products: MutableList<ApiProduct>,
    private val onAddToCartClick: (ApiProduct) -> Unit
) : RecyclerView.Adapter<ProductApiAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ApiProduct) {
            with(binding) {
                // Ajusta a URL da imagem
                val imageUrl = product.imageUrl?.let {
                    if (it.startsWith("//")) "https:$it" else it
                } ?: ""

                // Carregar imagem com Glide
                Glide.with(imgProduct.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder) // Use o mesmo placeholder para erro
                    .into(imgProduct)

                tvProductName.text = product.name ?: "Produto sem nome"

                // Formatar preço
                val priceText = product.price?.toDoubleOrNull()?.let {
                    "R$ %.2f".format(it)
                } ?: "Preço indisponível"

                tvProductPrice.text = priceText

                btnAddToCart.setOnClickListener { onAddToCartClick(product) }

                root.contentDescription = "Produto: ${product.name ?: "sem nome"}, Preço: $priceText"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<ApiProduct>) {
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }
}