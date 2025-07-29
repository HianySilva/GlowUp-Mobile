package com.example.glowup

import android.content.Context
import com.example.glowup.api.ApiProduct

object CartManager {

    private val cartProducts = mutableListOf<ApiProduct>()

    fun addToCart(context: Context, product: ApiProduct): Boolean {
        val alreadyInCart = cartProducts.any { it.id == product.id }

        return if (!alreadyInCart) {
            cartProducts.add(product)
            true
        } else {
            false
        }
    }

    fun removeProduct(product: ApiProduct) {
        cartProducts.removeAll { it.id == product.id }
    }

    fun getCartProducts(): List<ApiProduct> {
        return cartProducts.toList()
    }

    fun clearCart() {
        cartProducts.clear()
    }

    fun getCartSize(): Int {
        return cartProducts.size
    }
}
