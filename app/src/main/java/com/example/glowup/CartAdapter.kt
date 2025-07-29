package com.example.glowup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.glowup.api.ApiProduct
import com.example.glowup.databinding.ItemCartBinding

class CartAdapter(
    private val items: MutableList<ApiProduct>,
    private val onRemoveClick: (ApiProduct) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ApiProduct) {
            // Ajuste URL da imagem
            val imageUrl = product.imageUrl?.let {
                if (it.startsWith("//")) "https:$it" else it
            } ?: ""

            Glide.with(binding.imgCartProduct.context)
                .load(imageUrl)
                .placeholder(com.example.glowup.R.drawable.placeholder)
                .error(com.example.glowup.R.drawable.placeholder)
                .into(binding.imgCartProduct)

            binding.tvCartProductName.text = product.name ?: "Produto sem nome"

            val priceText = product.price?.toDoubleOrNull()?.let {
                "R$ %.2f".format(it)
            } ?: "Preço indisponível"

            binding.tvCartProductPrice.text = priceText

            // Botão remover com animação
            binding.btnRemove.setOnClickListener {
                val productToRemove = product
                val index = items.indexOf(productToRemove)
                if (index != -1) {
                    items.removeAt(index)
                    notifyItemRemoved(index)
                    onRemoveClick(productToRemove)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<ApiProduct>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
