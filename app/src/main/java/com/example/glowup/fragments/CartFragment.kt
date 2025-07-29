package com.example.glowup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glowup.CartAdapter
import com.example.glowup.api.ApiProduct
import com.example.glowup.databinding.FragmentCartBinding
import com.example.glowup.CartManager

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadCartItems()


        binding.btnCheckout.setOnClickListener {
            if (CartManager.getCartProducts().isEmpty()) {
                Toast.makeText(requireContext(), "Seu carrinho está vazio.", Toast.LENGTH_SHORT).show()
            } else {
                CartManager.clearCart()
                loadCartItems()
                Toast.makeText(requireContext(), "Pedido realizado com sucesso!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(mutableListOf()) { product ->
            CartManager.removeProduct(product)
            loadCartItems()
            Toast.makeText(requireContext(), "Produto removido do carrinho", Toast.LENGTH_SHORT).show()
        }

        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter
    }

    private fun loadCartItems() {
        val cartItems: List<ApiProduct> = CartManager.getCartProducts()
        adapter.updateItems(cartItems)

        binding.tvEmptyCart.visibility = if (cartItems.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
